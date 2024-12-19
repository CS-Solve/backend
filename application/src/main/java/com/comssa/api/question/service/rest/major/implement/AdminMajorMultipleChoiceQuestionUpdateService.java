package com.comssa.api.question.service.rest.major.implement;

import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminMajorMultipleChoiceQuestionUpdateService {
	private final MajorMultipleChoiceQuestionDbService majorMultipleChoiceQuestionDBService;

	public MajorMultipleChoiceQuestion toggleCanBeShortAnswered(Long questionId) {
		MajorMultipleChoiceQuestion majorMultipleChoiceQuestion = majorMultipleChoiceQuestionDBService
			.findById(
				questionId);
		majorMultipleChoiceQuestion.toggleCanBeShortAnswered();
		return majorMultipleChoiceQuestion;
	}

}
