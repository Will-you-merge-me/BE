package com.springboot.userserver.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import java.util.Objects;
import java.util.UUID;

@Component
public class S3UploadUtil {

    @Value("${cloud.aws.s3.bucketName}")
    private String bucket;
    private final AmazonS3 amazonS3;

    public S3UploadUtil(@Autowired AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }
    public URL fileUpload(MultipartFile multipartFile,String dirname){
        String fileName = makeFileName(multipartFile,dirname);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());

        try(InputStream inputStream = multipartFile.getInputStream()){
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        }catch(IOException e){
            throw new IllegalArgumentException("Image Upload Fail");
        }
        return amazonS3.getUrl(bucket,fileName);
    }

    private String makeFileName(MultipartFile multipartFile, String dirname){
        String originalName = multipartFile.getOriginalFilename();
        final String ext = Objects.requireNonNull(originalName).substring(originalName.lastIndexOf("."));
        final String fileName = UUID.randomUUID() + ext;
        return dirname + fileName;
    }
}