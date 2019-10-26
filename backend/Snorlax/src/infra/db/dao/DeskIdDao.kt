package com.yt8492.infra.db.dao

import com.yt8492.infra.db.table.DeskIds
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass

class DeskIdDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<DeskIdDao>(DeskIds)

    var deskId by DeskIds.deskId
}