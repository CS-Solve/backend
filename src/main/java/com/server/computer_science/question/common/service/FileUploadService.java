package com.server.computer_science.question.common.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface FileUploadService {
    public String uploadImage(MultipartFile file,String dirName) throws IOException;
}
