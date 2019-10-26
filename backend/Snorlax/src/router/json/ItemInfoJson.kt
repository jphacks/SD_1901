package com.yt8492.router.json

import kotlinx.serialization.Serializable

@Serializable
data class ItemInfoJson(
    val item_id: String,
    val name: String,
    val type: String,
    val qr_id: String?,
    val thumbnail_id: String?
)