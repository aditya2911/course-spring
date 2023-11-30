package com.example.coursespring.minio_service.service;



import com.jlefebure.spring.boot.minio.MinioFetchException;
import io.minio.DownloadObjectArgs;
import io.minio.GetObjectArgs;
import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.Result;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import io.minio.messages.Item;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MinioService {
    private final MinioClient minioClient;
    private final MinioConfigurationProperties configurationProperties;

    @Autowired
    public MinioService(  MinioConfigurationProperties configurationProperties) {
        this.minioClient =
                MinioClient.builder()
                        .endpoint("http://127.0.0.1:9000")
                        .credentials("kLCT3SoXFtABCAd8o9r3", "HeIDr6HaNf3LecD8lcdRd7nI2RODYrwKIijbc6qs")

                        .build();
        this.configurationProperties = configurationProperties;
    }

    public List list() {
        ListObjectsArgs args = (ListObjectsArgs)((ListObjectsArgs.Builder)ListObjectsArgs.builder().bucket(this.configurationProperties.getBucket())).prefix("").recursive(false).build();
        Iterable<Result<Item>> myObjects = this.minioClient.listObjects(args);
        return this.getItems(myObjects);
    }

    public List fullList() {
        ListObjectsArgs args = (ListObjectsArgs)((ListObjectsArgs.Builder)ListObjectsArgs.builder().bucket(this.configurationProperties.getBucket())).build();
        Iterable<Result<Item>> myObjects = this.minioClient.listObjects(args);
        return this.getItems(myObjects);
    }

    public List list(Path path) {
        ListObjectsArgs args = (ListObjectsArgs)((ListObjectsArgs.Builder)ListObjectsArgs.builder().bucket(this.configurationProperties.getBucket())).prefix(path.toString()).recursive(false).build();
        Iterable<Result<Item>> myObjects = this.minioClient.listObjects(args);
        return this.getItems(myObjects);
    }

    public List getFullList(Path path) {
        ListObjectsArgs args = (ListObjectsArgs)((ListObjectsArgs.Builder)ListObjectsArgs.builder().bucket(this.configurationProperties.getBucket())).prefix(path.toString()).build();
        Iterable<Result<Item>> myObjects = this.minioClient.listObjects(args);
        return this.getItems(myObjects);
    }

    private List getItems(Iterable<Result<Item>> myObjects) {
        return StreamSupport.stream(myObjects.spliterator(), true).map((itemResult) -> {
            try {
                return (Item)itemResult.get();
            } catch (Exception var2) {
                throw new MinioFetchException("Error while parsing list of objects", var2);
            }
        }).collect(Collectors.toList());
    }

    public InputStream get(Path path) throws MinioException {
        try {
            GetObjectArgs args = (GetObjectArgs)((GetObjectArgs.Builder)((GetObjectArgs.Builder)GetObjectArgs.builder().bucket(this.configurationProperties.getBucket())).object(path.toString())).build();
            return this.minioClient.getObject(args);
        } catch (Exception var3) {
            throw new MinioException();
        }
    }

    public StatObjectResponse getMetadata(Path path) throws MinioException {
        try {
            StatObjectArgs args = (StatObjectArgs)((StatObjectArgs.Builder)((StatObjectArgs.Builder)StatObjectArgs.builder().bucket(this.configurationProperties.getBucket())).object(path.toString())).build();
            return this.minioClient.statObject(args);
        } catch (Exception var3) {
            throw new MinioException();
        }
    }

    public Map getMetadata(Iterable<Path> paths) {
        return (Map)StreamSupport.stream(paths.spliterator(), false).map((path) -> {
            try {
                StatObjectArgs args = (StatObjectArgs)((StatObjectArgs.Builder)((StatObjectArgs.Builder)StatObjectArgs.builder().bucket(this.configurationProperties.getBucket())).object(path.toString())).build();
                return new AbstractMap.SimpleEntry(path, this.minioClient.statObject(args));
            } catch (Exception var3) {
                throw new MinioFetchException("Error while parsing list of objects", var3);
            }
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void getAndSave(Path source, String fileName) throws MinioException {
        try {
            DownloadObjectArgs args = (DownloadObjectArgs)((DownloadObjectArgs.Builder)((DownloadObjectArgs.Builder)DownloadObjectArgs.builder().bucket(this.configurationProperties.getBucket())).object(source.toString())).filename(fileName).build();
            this.minioClient.downloadObject(args);
        } catch (Exception var4) {
            throw new MinioException();
        }
    }

    public void upload(Path source, InputStream file, Map<String, String> headers) throws MinioException {
        try {
            PutObjectArgs args = (PutObjectArgs)((PutObjectArgs.Builder)((PutObjectArgs.Builder)((PutObjectArgs.Builder)PutObjectArgs.builder().bucket(this.configurationProperties.getBucket())).object(source.toString())).stream(file, (long)file.available(), -1L).headers(headers)).build();
            this.minioClient.putObject(args);
        } catch (Exception var5) {
            throw new MinioException();
        }
    }

    public void upload(Path source, InputStream file) throws MinioException {
        try {
            PutObjectArgs args = (PutObjectArgs)((PutObjectArgs.Builder)((PutObjectArgs.Builder)PutObjectArgs.builder().bucket(this.configurationProperties.getBucket())).object(source.toString())).stream(file, (long)file.available(), -1L).build();
            this.minioClient.putObject(args);
        } catch (Exception var4) {
            throw new MinioException();
        }
    }

    public void upload(Path source, InputStream file, String contentType, Map<String, String> headers) throws MinioException {
        try {
            PutObjectArgs args = (PutObjectArgs)((PutObjectArgs.Builder)((PutObjectArgs.Builder)((PutObjectArgs.Builder)PutObjectArgs.builder().bucket(this.configurationProperties.getBucket())).object(source.toString())).stream(file, (long)file.available(), -1L).headers(headers)).contentType(contentType).build();
            this.minioClient.putObject(args);
        } catch (Exception var6) {
            throw new MinioException();
        }
    }

    public void upload(Path source, InputStream file, String contentType) throws MinioException {
        try {
            PutObjectArgs args = (PutObjectArgs)((PutObjectArgs.Builder)((PutObjectArgs.Builder)PutObjectArgs.builder().bucket(this.configurationProperties.getBucket())).object(source.toString())).stream(file, (long)file.available(), -1L).contentType(contentType).build();
            this.minioClient.putObject(args);
        } catch (Exception var5) {
            throw new MinioException();
        }
    }

    public void upload(Path source, File file) throws MinioException {
        try {
            UploadObjectArgs args = (UploadObjectArgs)((UploadObjectArgs.Builder)((UploadObjectArgs.Builder)UploadObjectArgs.builder().bucket(this.configurationProperties.getBucket())).object(source.toString())).filename(file.getAbsolutePath()).build();
            this.minioClient.uploadObject(args);
        } catch (Exception var4) {
            throw new MinioException();
        }
    }

    public void remove(Path source) throws MinioException {
        try {
            RemoveObjectArgs args = (RemoveObjectArgs)((RemoveObjectArgs.Builder)((RemoveObjectArgs.Builder)RemoveObjectArgs.builder().bucket(this.configurationProperties.getBucket())).object(source.toString())).build();
            this.minioClient.removeObject(args);
        } catch (Exception var3) {
            throw new MinioException();
        }
    }}