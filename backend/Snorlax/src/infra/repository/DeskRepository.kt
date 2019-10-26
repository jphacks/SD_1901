package com.yt8492.infra.repository

import com.yt8492.infra.db.dao.DeskIdDao
import com.yt8492.infra.db.table.DeskIds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.transactions.transaction

object DeskRepository {
    suspend fun isNotExist(deskId: String): Boolean = withContext(Dispatchers.IO) {
        transaction {
            DeskIdDao.find {
                DeskIds.deskId eq deskId
            }.singleOrNull() == null
        }
    }

    suspend fun save(deskId: String) {
        withContext(Dispatchers.IO) {
            transaction {
                DeskIdDao.new {
                    this.deskId = deskId
                }
            }
        }
    }
}