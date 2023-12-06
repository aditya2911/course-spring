package com.example.coursespring.post_files.controllers;



import com.example.coursespring.minio_service.service.MinioService;
import io.minio.errors.MinioException;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/v1/files")

public class PostFileController {

    private final MinioService minioService;

    @Autowired
    PostFileController(MinioService ms){
    this.minioService = ms;
}
  @GetMapping("images/get/{imageName}")
 public void getImageByName( @PathVariable("imageName") String image,HttpServletResponse response) throws MinioException {
        String object = image;
      InputStream inputStream = minioService.get(Path.of(object));
      InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

      // Set the content type and attachment header.
      response.addHeader("Content-disposition", "attachment;filename=" + object);
      response.setContentType(URLConnection.guessContentTypeFromName(object));

      // Copy the stream to the response's output stream.
      try {
          IOUtils.copy(inputStream, response.getOutputStream());
      } catch (IOException e) {
          throw new RuntimeException(e);
      }
      try {
          response.flushBuffer();
      } catch (IOException e) {
          throw new RuntimeException(e);
      }


  }


    @PostMapping("/images/post")
    public void postImage(@RequestParam("file") MultipartFile file) {
        Path path = Path.of(file.getOriginalFilename());
        try {
            minioService.upload(path, file.getInputStream(), file.getContentType());
        } catch (MinioException e) {
            throw new IllegalStateException("The file cannot be upload on the internal storage. Please retry later", e);
        } catch (IOException e) {
            throw new IllegalStateException("The file cannot be read", e);
        }
    }
 }
