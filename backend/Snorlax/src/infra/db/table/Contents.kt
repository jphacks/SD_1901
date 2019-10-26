package com.yt8492.infra.db.table

import org.jetbrains.exposed.dao.LongIdTable

object Contents : LongIdTable() {
    val itemId = text("itemId")
    val content = text("content")
}