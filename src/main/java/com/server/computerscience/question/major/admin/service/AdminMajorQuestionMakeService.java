package com.server.computerscience.question.major.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.server.computerscience.question.major.admin.dto.RequestMakeMultipleChoiceQuestionDto;
import com.server.computerscience.question.major.common.domain.MajorMultipleChoiceQuestion;
import com.server.computerscience.question.major.common.exception.DuplicateQuestionException;

@Service
public interface AdminMajorQuestionMakeService {

	public List<MajorMultipleChoiceQuestion> makeMultipleChoiceQuestions(
		List<RequestMakeMultipleChoiceQuestionDto> requestNormalQuestionDto);

	public MajorMultipleChoiceQuestion makeMultipleChoiceQuestion(
		RequestMakeMultipleChoiceQuestionDto requestNormalQuestionDto) throws DuplicateQuestionException;
}
