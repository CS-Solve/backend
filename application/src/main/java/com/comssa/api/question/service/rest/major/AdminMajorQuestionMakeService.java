package com.comssa.api.question.service.rest.major;


import com.comssa.api.exception.DuplicateQuestionContentException;
import com.comssa.persistence.question.domain.major.MajorDescriptiveQuestion;
import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion;
import com.comssa.persistence.question.dto.common.request.RequestMakeMultipleChoiceQuestionDto;
import com.comssa.persistence.question.dto.major.request.RequestMakeMajorDescriptiveQuestionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminMajorQuestionMakeService {

	List<MajorMultipleChoiceQuestion> makeMultipleChoiceQuestions(
		List<RequestMakeMultipleChoiceQuestionDto> requestNormalQuestionDto);

	MajorMultipleChoiceQuestion makeMultipleChoiceQuestion(
		RequestMakeMultipleChoiceQuestionDto requestNormalQuestionDto) throws DuplicateQuestionContentException;

	List<MajorDescriptiveQuestion> makeDescriptiveQuestions(
		List<RequestMakeMajorDescriptiveQuestionDto> requestNormalQuestionDto
	);
}
