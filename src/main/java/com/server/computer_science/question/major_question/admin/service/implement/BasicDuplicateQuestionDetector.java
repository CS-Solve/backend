package com.server.computer_science.question.major_question.admin.service.implement;

import org.springframework.stereotype.Service;

import com.server.computer_science.question.major_question.admin.service.DuplicateQuestionDetector;

@Service
public class BasicDuplicateQuestionDetector implements DuplicateQuestionDetector {

	@Override
	public boolean isQuestionDuplicate(String originalString, String newString) {
		originalString = originalString.replaceAll("\\s", "");
		newString = newString.replaceAll("\\s", "");
		return originalString.equals(newString);
	}
}
