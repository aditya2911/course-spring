package com.example.coursespring.minio_service.service;


import java.time.Duration;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration("spring.minio")
public class MinioConfigurationProperties {
    private String url = "http://127.0.0.1:9000";
    private String accessKey = "kLCT3SoXFtABCAd8o9r3";
    private String secretKey = "HeIDr6HaNf3LecD8lcdRd7nI2RODYrwKIijbc6qs";
    private boolean secure = false;
    private String bucket = "default-bucket";
    private String metricName = "minio.storage";
    private Duration connectTimeout = Duration.ofSeconds(10L);
    private Duration writeTimeout = Duration.ofSeconds(60L);
    private Duration readTimeout = Duration.ofSeconds(10L);
    private boolean checkBucket = true;
    private boolean createBucket = true;

    public MinioConfigurationProperties() {
    }

    public void setConnectTimeout(Duration connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setWriteTimeout(Duration writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    public void setReadTimeout(Duration readTimeout) {
        this.readTimeout = readTimeout;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public void setCheckBucket(boolean checkBucket) {
        this.checkBucket = checkBucket;
    }

    public void setCreateBucket(boolean createBucket) {
        this.createBucket = createBucket;
    }
}
