package com.yt8492.model

data class ItemInfo(
    val itemId: String,
    val name: String,
    val type: ContentType,
    val qrId: String?,
    val thumbnailId: String?
)