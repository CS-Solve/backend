package com.server.computerscience.question.major.admin.service.implement;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.computerscience.question.common.service.QuestionUpdateService;
import com.server.computerscience.question.major.common.domain.MajorMultipleChoiceQuestion;
import com.server.computerscience.question.major.common.service.implement.MajorMultipleChoiceQuestionDBService;

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
