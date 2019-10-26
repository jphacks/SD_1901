package com.yt8492.infra.repository

import com.yt8492.infra.db.dao.ContentDao
import com.yt8492.infra.db.table.Contents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.transactions.transaction

object ContentRepository {
    suspend fun findByItemId(
        itemId: String
    ): String? = withContext(Dispatchers.IO) {
        transaction {
            ContentDao.find {
                Contents.itemId eq itemId
            }.singleOrNull()?.content
        }
    }

    suspend fun save(
        itemId: String,
        content: String
    ) {
        withContext(Dispatchers.IO) {
            transaction {
                ContentDao.new {
                    this.itemId = itemId
                    this.content = content
                }
            }
        }
    }
}