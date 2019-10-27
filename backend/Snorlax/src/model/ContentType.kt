package com.yt8492.model

sealed class ContentType(val value: String) {
    sealed class NotFile(type: String) : ContentType(type) {
        object Text : NotFile("text")
        object Uri : NotFile("url")
    }
    sealed class File(type: String) : ContentType(type) {
        object Image : File("image")
        object Video : File("video")
        object Audio : File("audio")
        object Other : File("file")
    }

    companion object {
        fun convert(type: String): ContentType {
             return when {
                type == "text" -> NotFile.Text
                type == "url" -> NotFile.Uri
                type.startsWith("image") -> File.Image
                type.startsWith("video") -> File.Video
                type.startsWith("audio") -> File.Audio
                else -> File.Other
            }
        }
    }
}
