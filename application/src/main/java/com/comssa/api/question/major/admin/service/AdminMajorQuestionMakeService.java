package com.comssa.api.question.major.admin.service;


import com.comssa.api.question.major.common.exception.DuplicateQuestionException;
import com.comssa.persistence.question.major.admin.dto.RequestMakeMultipleChoiceQuestionDto;
import com.comssa.persistence.question.major.domain.common.MajorMultipleChoiceQuestion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminMajorQuestionMakeService {

	List<MajorMultipleChoiceQuestion> makeMultipleChoiceQuestions(
		List<RequestMakeMultipleChoiceQuestionDto> requestNormalQuestionDto);

	MajorMultipleChoiceQuestion makeMultipleChoiceQuestion(
		RequestMakeMultipleChoiceQuestionDto requestNormalQuestionDto) throws DuplicateQuestionException;
}
