package com.study.computerscience.question.major.admin.service;

import com.study.computerscience.question.major.admin.dto.RequestMakeMultipleChoiceQuestionDto;
import com.study.computerscience.question.major.common.domain.MajorMultipleChoiceQuestion;
import com.study.computerscience.question.major.common.exception.DuplicateQuestionException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminMajorQuestionMakeService {

	List<MajorMultipleChoiceQuestion> makeMultipleChoiceQuestions(
		List<RequestMakeMultipleChoiceQuestionDto> requestNormalQuestionDto);

	MajorMultipleChoiceQuestion makeMultipleChoiceQuestion(
		RequestMakeMultipleChoiceQuestionDto requestNormalQuestionDto) throws DuplicateQuestionException;
}
