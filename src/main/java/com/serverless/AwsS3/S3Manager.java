package com.serverless.AwsS3;

import java.nio.ByteBuffer;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class S3Manager {
    private final S3Client S3;

    public S3Manager() {
        S3 = S3Client.builder()
            .region(Region.AP_NORTHEAST_1)
            .build();
    }

    public void putObject(String key, String bucketName, ByteBuffer buffer) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .build();

        S3.putObject(objectRequest, RequestBody.fromByteBuffer(buffer));        
    }
}
