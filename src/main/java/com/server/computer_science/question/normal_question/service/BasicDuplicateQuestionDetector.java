package com.server.computer_science.question.normal_question.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicDuplicateQuestionDetector implements DuplicateQuestionDetector {

    @Override
    public boolean isQuestionDuplicate(String originalString, String newString){
        originalString = originalString.replaceAll("\\s","");
        newString = newString.replaceAll("\\s","");
        return originalString.equals(newString);
    }
}
