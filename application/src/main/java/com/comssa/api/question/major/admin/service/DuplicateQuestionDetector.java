package com.comssa.api.question.major.admin.service;

import org.springframework.stereotype.Service;

@Service
public interface DuplicateQuestionDetector {
    boolean isQuestionDuplicate(String originalString, String newQuestions);
}
