package com.yt8492.router.json

import kotlinx.serialization.Serializable

@Serializable
data class ItemInfoListJson(
    val item_info_list: List<ItemInfoJson>
)