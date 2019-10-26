package com.yt8492.infra.db.dao

import com.yt8492.infra.db.table.ItemInfos
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass

class ItemInfoDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<ItemInfoDao>(ItemInfos)

    var itemId by ItemInfos.itemId
    var deskId by ItemInfos.deskId
    var name by ItemInfos.name
    var type by ItemInfos.type
    var qrId by ItemInfos.qrId
    var thumbnailId by ItemInfos.thumbnailId
}