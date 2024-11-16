package com.api.computerscience.question.major.admin.service;


import com.api.computerscience.question.major.common.exception.DuplicateQuestionException;
import com.persistence.computerscience.question.major.domain.admin.dto.RequestMakeMultipleChoiceQuestionDto;
import com.persistence.computerscience.question.major.domain.common.MajorMultipleChoiceQuestion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminMajorQuestionMakeService {

	List<MajorMultipleChoiceQuestion> makeMultipleChoiceQuestions(
		List<RequestMakeMultipleChoiceQuestionDto> requestNormalQuestionDto);

	MajorMultipleChoiceQuestion makeMultipleChoiceQuestion(
		RequestMakeMultipleChoiceQuestionDto requestNormalQuestionDto) throws DuplicateQuestionException;
}
