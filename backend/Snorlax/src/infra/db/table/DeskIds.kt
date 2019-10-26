package com.yt8492.infra.db.table

import org.jetbrains.exposed.dao.LongIdTable

object DeskIds : LongIdTable() {
    val deskId = text("deskId").uniqueIndex()
}