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
        fun convert(type: String): ContentType = when (type) {
            "text" -> NotFile.Text
            "url" -> NotFile.Uri
            "image" -> File.Image
            "video" -> File.Video
            "audio" -> File.Audio
            else -> File.Other
        }
    }
}
