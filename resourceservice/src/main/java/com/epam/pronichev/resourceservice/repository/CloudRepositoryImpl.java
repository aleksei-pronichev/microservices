package com.epam.pronichev.resourceservice.repository;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.epam.pronichev.resourceservice.exceptions.ResourceAccessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Repository
@RequiredArgsConstructor
public class CloudRepositoryImpl implements CloudRepository {

    private final AmazonS3 amazonS3;

    @Value("${cloud.s3.bucket}")
    private String bucketName;

    @Override
    public void save(String key, byte[] data) {
        var inputStream = new ByteArrayInputStream(data);
        var metadata = new ObjectMetadata();
        metadata.setContentLength(data.length);
        amazonS3.putObject(bucketName, key, inputStream, metadata);
    }

    @Override
    public byte[] get(String key) {
        try {
            S3Object s3Object = amazonS3.getObject(bucketName, key);
            return IOUtils.toByteArray(s3Object.getObjectContent());
        } catch (IOException e) {
            throw new ResourceAccessException(e.getMessage());
        }
    }

    @PostConstruct
    private void createBucket() {
        if (!amazonS3.doesBucketExistV2(bucketName)) {
            amazonS3.createBucket(bucketName);
        }
    }
}
