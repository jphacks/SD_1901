package com.yt8492.infra.db.dao

import com.yt8492.infra.db.table.Contents
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass

class ContentDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<ContentDao>(Contents)

    var itemId by Contents.itemId
    var content by Contents.content
}