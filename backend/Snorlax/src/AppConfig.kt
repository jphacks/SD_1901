package com.yt8492

import com.typesafe.config.ConfigFactory
import io.ktor.config.HoconApplicationConfig
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
object AppConfig {
    private val appConfig = HoconApplicationConfig(ConfigFactory.load())
    val dbUrl = appConfig.property("ktor.db.jdbcUrl").getString()
    val dbUser = appConfig.property("ktor.db.dbUser").getString()
    val dbPassword = appConfig.property("ktor.db.dbPassword").getString()
    val s3Url = appConfig.property("ktor.aws.s3Url").getString()
    val awsAccessKeyId = appConfig.property("ktor.aws.awsAccessKeyId").getString()
    val awsSecretAccessKey = appConfig.property("ktor.aws.awsSecretAccessKey").getString()
}