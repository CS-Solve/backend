package com.comssa.api.question.major.admin.service.implement;

import com.comssa.api.question.major.common.service.implement.MajorMultipleChoiceQuestionDbService;
import com.comssa.persistence.question.major.domain.common.MajorMultipleChoiceQuestion;
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
