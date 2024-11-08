package com.server.computerscience.question.common.service;

import org.springframework.stereotype.Service;

@Service
public interface QuestionExternalService {
	public void sendQuestion(String command);
}
