package com.comssa.api.question.service.common;

import org.springframework.stereotype.Service;

@Service
public interface DuplicateQuestionDetector {
	boolean isQuestionDuplicate(String originalString, String newQuestions);
}
