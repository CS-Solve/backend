package com.server.computer_science.question.major_question.admin.service.implement;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.computer_science.question.common.service.QuestionUpdateService;
import com.server.computer_science.question.major_question.common.domain.MajorMultipleChoiceQuestion;
import com.server.computer_science.question.major_question.common.service.implement.MajorMultipleChoiceQuestionDBService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminMajorMultipleChoiceQuestionUpdateService
	implements QuestionUpdateService<MajorMultipleChoiceQuestion> {
	private final MajorMultipleChoiceQuestionDBService majorMultipleChoiceQuestionDBService;

	public MajorMultipleChoiceQuestion toggleApproveNormalQuestion(Long questionId) {
		MajorMultipleChoiceQuestion majorMultipleChoiceQuestion = majorMultipleChoiceQuestionDBService.findByIdFetchChoices(
			questionId);
		majorMultipleChoiceQuestion.toggleApproved();
		return majorMultipleChoiceQuestion;
	}

	public MajorMultipleChoiceQuestion toggleCanBeShortAnswered(Long questionId) {
		MajorMultipleChoiceQuestion majorMultipleChoiceQuestion = majorMultipleChoiceQuestionDBService.findByIdFetchChoices(
			questionId);
		majorMultipleChoiceQuestion.toggleCanBeShortAnswered();
		return majorMultipleChoiceQuestion;
	}

	@Override
	public void deleteQuestion(Long questionId) {
		majorMultipleChoiceQuestionDBService.deleteById(questionId);
	}

	@Override
	public MajorMultipleChoiceQuestion findById(Long questionId) {
		return majorMultipleChoiceQuestionDBService.findByIdFetchChoices(questionId);
	}

}
