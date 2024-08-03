package com.example.productserver.Service;

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
            objectMetadata.setContentType(multipartFile.getContentType()); //객체(파일)의 메타데이터 설정
			/*
              1. multipartFile로부터 데이터를 읽은 후 PutObjectRequest 객체를 생성한다.
              2. 이때 파일을 외부에서 읽을 수 있도록 하기 위해 withCannedAcl을 설정한다.(개별 파일에 cannedAcl(사전 정의된 ACL)을 적용한다는 의미이다.)
              3. putObject메소드를 이용해 생성된 객체를 AWS S3로 전송한다.
            */
            try(InputStream inputStream = multipartFile.getInputStream()){
                amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            }catch(IOException e){
                //파일 생성 및 업로드 과정에서 발생한 예외에 대해 적절한 처리를 해준다.
                throw new IllegalArgumentException("Image Upload Fail");
            }
            //bucket으로 부터 key(file명)에 대한 url 정보를 가져온다.
        return amazonS3.getUrl(bucket,fileName);
    }

    // 파일 이름이 같으면 저장이 안됨. 따라서 파일이름 앞에 UUID를 붙인다.
    private String makeFileName(MultipartFile multipartFile, String dirname){
        String originalName = multipartFile.getOriginalFilename();
        final String ext = Objects.requireNonNull(originalName).substring(originalName.lastIndexOf("."));
        final String fileName = UUID.randomUUID() + ext;
        return dirname + fileName;
    }



}
