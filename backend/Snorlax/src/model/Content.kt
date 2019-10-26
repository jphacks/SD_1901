package com.yt8492.model

sealed class Content {
    class File(val file: java.io.File) : Content()
    class Text(val value: String) : Content()
}