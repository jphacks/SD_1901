package com.yt8492.service

import com.yt8492.AppConfig
import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import software.amazon.awssdk.auth.credentials.AwsCredentials
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.*
import java.io.File
import java.net.URI
import java.util.*

@KtorExperimentalAPI
object S3Service {
    private const val bucketName = "snorlax"
    private val s3 = S3Client.builder()
        .region(Region.US_EAST_1)
        .credentialsProvider {
            object : AwsCredentials {
                override fun accessKeyId(): String = AppConfig.awsAccessKeyId

                override fun secretAccessKey(): String = AppConfig.awsSecretAccessKey
            }
        }
        .endpointOverride(AppConfig.s3Url.let(URI::create))
        .build()

    init {
        try {
            s3.createBucket {
                it.bucket(bucketName)
                    .acl(BucketCannedACL.PUBLIC_READ)
                    .build()
            }
        } catch (e: BucketAlreadyOwnedByYouException) {
            println("bucket already created")
        } catch (e: BucketAlreadyExistsException) {
            println("bucket already exist")
        }
    }

    suspend fun postFile(
        deskId: String,
        file: File
    ): String = withContext(Dispatchers.IO) {
        val name = UUID.randomUUID().toString().replace("-", "")
        val ext = file.extension
        val itemId = "$name.$ext"
        val key = "$deskId/$itemId"
        val req = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .acl(ObjectCannedACL.PUBLIC_READ)
            .build()

        s3.putObject(req, RequestBody.fromFile(file))
        itemId
    }

    suspend fun getFile(
        deskId: String,
        itemId: String,
        name: String
    ): File = withContext(Dispatchers.IO) {
        val res = s3.getObject {
            it.bucket(bucketName)
                .key("$deskId/$itemId")
                .build()
        }
        val file = File(name)
        res.buffered().use { input ->
            file.outputStream().buffered().use { output ->
                input.copyTo(output)
            }
        }
        file
    }
}