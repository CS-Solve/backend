package com.server.computerscience.question.common.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileUploadService {
	public String uploadImage(MultipartFile file, String dirName) throws IOException;
}
