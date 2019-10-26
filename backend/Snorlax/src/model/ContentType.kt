package com.yt8492.model

sealed class ContentType(val value: String) {
    sealed class NotFile(type: String) : ContentType(type) {
        object Text : NotFile("text")
        object Uri : NotFile("url")
    }
    sealed class File(type: String) : ContentType(type) {
        class Image(subType: String) : File("image/$subType")
        class Video(subType: String) : File("video/$subType")
        class Audio(subType: String) : File("audio/$subType")
        object Other : File("file")
    }

    companion object {
        fun convert(type: String): ContentType {
            val subType = type.split("/").getOrNull(1)
             return when {
                type == "text" -> NotFile.Text
                type == "url" -> NotFile.Uri
                type.startsWith("image") && subType != null -> File.Image(subType)
                type.startsWith("video") && subType != null -> File.Video(subType)
                type.startsWith("audio") && subType != null -> File.Audio(subType)
                else -> File.Other
            }
        }
    }
}
