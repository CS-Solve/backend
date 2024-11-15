package com.server.computerscience.question.common.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface FileUploadService {
	String uploadImage(MultipartFile file, String dirName) throws IOException;
}
