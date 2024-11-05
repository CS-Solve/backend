package com.server.computerscience.question.major.admin.service;

import org.springframework.stereotype.Service;

@Service
public interface DuplicateQuestionDetector {
	public boolean isQuestionDuplicate(String originalString, String newQuestions);
}
