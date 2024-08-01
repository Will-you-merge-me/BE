package com.example.productserver.Service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Component
public class S3UploadUtil {

    private static final Logger log = LoggerFactory.getLogger(S3UploadUtil.class);
    @Value("${cloud.aws.s3.bucket")
    private String bucket;
    private AmazonS3Client amazonS3Client;

    public Optional<File> convert(MultipartFile image) throws IOException {
        File convertFile = new File(Objects.requireNonNull(image.getOriginalFilename()));
        if(convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)){
                fos.write(image.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }

    public String upload(File uploadFile, String dirName) {
        String fileName = dirName + uploadFile.getName();
        String uploadImageUrl = putS3(uploadFile, fileName);

        removeNewFile(uploadFile);

        return uploadImageUrl;
    }

    private String putS3(File uploadFile, String filename){
        amazonS3Client.putObject(
                new PutObjectRequest(bucket, filename, uploadFile)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );
        return amazonS3Client.getUrl(bucket, filename).toString();
    }

    private void removeNewFile(File targetFile){
        if(targetFile.delete()){
            log.info("Delete File Success");
        }
        else {
            log.info("Delete File Failed");
        }
    }
}
