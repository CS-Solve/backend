package com.comssa.api.question.service.common.implement;

import com.comssa.api.question.service.common.DuplicateQuestionDetector;
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
