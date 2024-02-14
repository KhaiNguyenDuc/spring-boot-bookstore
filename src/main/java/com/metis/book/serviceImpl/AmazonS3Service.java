package com.metis.book.serviceImpl;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.metis.book.dto.FileResponse;
import com.metis.book.utils.AppConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;

@Service
@Slf4j
public class AmazonS3Service {
    private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.region}")
    private String region;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client =  AmazonS3ClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart, String id) {
        return  new Date().getTime() + id + "-" + Objects.requireNonNull(multiPart.getOriginalFilename()).replace(" ", "_");
    }
    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }
    public FileResponse uploadFile(MultipartFile multipartFile, Long id, String folder) {

        String fileUrl = "";
        String fileName = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            fileName = generateFileName(multipartFile, String.valueOf(id));
            fileUrl = endpointUrl + "/" + fileName;
            uploadFileTos3bucket(folder + "/" + fileName, file);
            file.delete();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        FileResponse fileResponse = new FileResponse();
        fileResponse.setFileName(fileName);
        fileResponse.setUrl(fileUrl);
        return fileResponse;
    }
    public FileResponse saveUserImage(MultipartFile file, Long userId)  {
          return uploadFile(file, userId, AppConstant.UPLOAD_USER_DIRECTORY);
    }
    public FileResponse saveBookImage(MultipartFile file, Long bookId)  {
        return uploadFile(file, bookId, AppConstant.UPLOAD_BOOK_DIRECTORY);
    }
    public FileResponse saveBlogImage(MultipartFile file, Long blogId) {
        return uploadFile(file, blogId, AppConstant.UPLOAD_BLOG_DIRECTORY);
    }
    public FileResponse saveCategoryImage(MultipartFile file, Long categoryId) {
        return uploadFile(file, categoryId, AppConstant.UPLOAD_CATEGORY_DIRECTORY);
    }
    public void deleteFile(String fileName, String folder) {
        s3client.deleteObject(new DeleteObjectRequest(bucketName, folder + "/" + fileName));
    }
}
