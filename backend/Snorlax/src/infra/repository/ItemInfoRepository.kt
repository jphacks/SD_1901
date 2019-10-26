package com.yt8492.infra.repository

import com.yt8492.infra.db.dao.ItemInfoDao
import com.yt8492.infra.db.table.ItemInfos
import com.yt8492.model.ContentType
import com.yt8492.model.ItemInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

object ItemInfoRepository {
    suspend fun isExist(
        deskId: String,
        name: String
    ): Boolean = withContext(Dispatchers.IO) {
        transaction {
            ItemInfoDao.find {
                (ItemInfos.name eq name) and (ItemInfos.deskId eq deskId)
            }.empty().not()
        }
    }

    suspend fun deleteByDeskIdAndName(
        deskId: String,
        name: String
    ) {
        withContext(Dispatchers.IO) {
            transaction {
                ItemInfoDao.find {
                    (ItemInfos.name eq name) and (ItemInfos.deskId eq deskId)
                }.forEach {
                    it.delete()
                }
            }
        }
    }

    suspend fun findAllByDeskId(
        deskId: String
    ): List<ItemInfo> = withContext(Dispatchers.IO) {
        transaction {
            ItemInfoDao.find {
                ItemInfos.deskId eq deskId
            }.map(this@ItemInfoRepository::dao2Model)
        }
    }

    suspend fun findByItemId(
        itemId: String
    ): ItemInfo? = withContext(Dispatchers.IO) {
        transaction {
            ItemInfoDao.find {
                ItemInfos.itemId eq itemId
            }.singleOrNull()?.let(this@ItemInfoRepository::dao2Model)
        }
    }

    suspend fun save(
        itemId: String,
        deskId: String,
        name: String,
        type: String,
        qrId: String?,
        thumbnailId: String?
    ): ItemInfo = withContext(Dispatchers.IO) {
        transaction {
            ItemInfoDao.new {
                this.itemId = itemId
                this.deskId = deskId
                this.name = name
                this.type = type
                this.qrId = qrId
                this.thumbnailId = thumbnailId
            }.let(this@ItemInfoRepository::dao2Model)
        }
    }

    private fun dao2Model(
        itemInfoDao: ItemInfoDao
    ): ItemInfo = ItemInfo(
        itemId = itemInfoDao.itemId,
        name = itemInfoDao.name,
        type = ContentType.convert(itemInfoDao.type),
        qrId = itemInfoDao.qrId,
        thumbnailId = itemInfoDao.qrId
    )
}