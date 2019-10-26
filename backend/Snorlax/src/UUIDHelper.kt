package com.yt8492

import java.util.*

object UUIDHelper {
    fun createUUID(): String = UUID.randomUUID().toString().replace("-", "")
}