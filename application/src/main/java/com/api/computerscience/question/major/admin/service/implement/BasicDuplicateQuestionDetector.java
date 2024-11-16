package com.api.computerscience.question.major.admin.service.implement;

import com.api.computerscience.question.major.admin.service.DuplicateQuestionDetector;
import org.springframework.stereotype.Service;

@Service
public class BasicDuplicateQuestionDetector implements DuplicateQuestionDetector {

    @Override
    public boolean isQuestionDuplicate(String originalString, String newString) {
        originalString = originalString.replaceAll("\\s", "");
        newString = newString.replaceAll("\\s", "");
        return originalString.equals(newString);
    }
}
