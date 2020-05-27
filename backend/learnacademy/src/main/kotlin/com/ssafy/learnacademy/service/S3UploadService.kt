package com.ssafy.learnacademy.service

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.util.*
import javax.annotation.PostConstruct


@Service
class S3UploadService {

    var s3Client: AmazonS3? = null

    @Value("\${cloud.aws.credentials.accessKey}")
    private val accessKey: String? = null

    @Value("\${cloud.aws.credentials.secretKey}")
    private val secretKey: String? = null

    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String? = null

    @Value("\${cloud.aws.region.static}")
    private val region: String? = null

    @PostConstruct
    fun setS3Client() {
        val credentials: AWSCredentials = BasicAWSCredentials(this.accessKey, this.secretKey)
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build()
    }

    @Throws(IOException::class)
    fun uploadFile(file: MultipartFile?, dirName: String): String? {
        val fileName = dirName + UUID.randomUUID()+".jpg"
        s3Client!!.putObject(PutObjectRequest(bucket, fileName, file?.inputStream, null)
                .withCannedAcl(CannedAccessControlList.PublicRead))
        return s3Client!!.getUrl(bucket, fileName).toString()
    }
}
