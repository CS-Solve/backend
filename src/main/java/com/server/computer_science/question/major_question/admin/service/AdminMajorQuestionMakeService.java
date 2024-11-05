package com.server.computer_science.question.major_question.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.server.computer_science.question.major_question.admin.dto.RequestMakeMultipleChoiceQuestionDto;
import com.server.computer_science.question.major_question.common.domain.MajorMultipleChoiceQuestion;
import com.server.computer_science.question.major_question.common.exception.DuplicateQuestionException;

@Service
public interface AdminMajorQuestionMakeService {

	public List<MajorMultipleChoiceQuestion> makeMultipleChoiceQuestions(
		List<RequestMakeMultipleChoiceQuestionDto> requestNormalQuestionDto);

	public MajorMultipleChoiceQuestion makeMultipleChoiceQuestion(
		RequestMakeMultipleChoiceQuestionDto requestNormalQuestionDto) throws DuplicateQuestionException;
}
