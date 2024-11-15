package com.server.computerscience.question.major.service;

import com.server.computerscience.question.major.admin.service.implement.BasicDuplicateQuestionDetector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("[단위] 기본 문장 유사도 검사")
@ExtendWith(MockitoExtension.class)
class BasicDuplicateQuestionDetectorTest {
	@InjectMocks
	private BasicDuplicateQuestionDetector basicDuplicateQuestionDetector;

	@DisplayName("문장 유사도 검사 - 빈칸 제거 후 String 동일성 검사")
	@Test
	void isStringDuplicate() {
		String originalString = "문장 유사도 검사";
		String newString = "문장유사도검사";
		Assertions.assertTrue(basicDuplicateQuestionDetector.isQuestionDuplicate(originalString, newString));
	}
}
