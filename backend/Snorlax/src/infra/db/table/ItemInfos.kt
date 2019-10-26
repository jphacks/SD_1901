package com.yt8492.infra.db.table

import org.jetbrains.exposed.dao.LongIdTable

object ItemInfos : LongIdTable() {
    val itemId = text("itemId").uniqueIndex()
    val deskId = text("deskId")
    val name = text("name")
    val type = text("type")
    val qrId = text("qrId").nullable()
    val thumbnailId = text("thumbnailId").nullable()
}