package com.comssa.api.question.major.admin.service;


import com.comssa.api.question.major.common.exception.DuplicateQuestionException;
import com.comssa.persistence.question.major.admin.dto.RequestMakeMajorDescriptiveQuestionDto;
import com.comssa.persistence.question.major.admin.dto.RequestMakeMajorMultipleChoiceQuestionDto;
import com.comssa.persistence.question.major.domain.common.MajorDescriptiveQuestion;
import com.comssa.persistence.question.major.domain.common.MajorMultipleChoiceQuestion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminMajorQuestionMakeService {

	List<MajorMultipleChoiceQuestion> makeMultipleChoiceQuestions(
		List<RequestMakeMajorMultipleChoiceQuestionDto> requestNormalQuestionDto);

	MajorMultipleChoiceQuestion makeMultipleChoiceQuestion(
		RequestMakeMajorMultipleChoiceQuestionDto requestNormalQuestionDto) throws DuplicateQuestionException;

	List<MajorDescriptiveQuestion> makeDescriptiveQuestions(
		List<RequestMakeMajorDescriptiveQuestionDto> requestNormalQuestionDto
	);
}
