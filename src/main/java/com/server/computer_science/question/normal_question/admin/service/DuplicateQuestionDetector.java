package com.server.computer_science.question.normal_question.admin.service;

import org.springframework.stereotype.Service;

@Service
public interface DuplicateQuestionDetector {
    public boolean isQuestionDuplicate(String originalString,String newQuestions);
}
