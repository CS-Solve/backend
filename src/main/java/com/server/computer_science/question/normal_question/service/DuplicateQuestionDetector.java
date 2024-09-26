package com.server.computer_science.question.normal_question.service;

import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DuplicateQuestionDetector {
    public boolean isQuestionDuplicate(String originalString,String newQuestions);
}
