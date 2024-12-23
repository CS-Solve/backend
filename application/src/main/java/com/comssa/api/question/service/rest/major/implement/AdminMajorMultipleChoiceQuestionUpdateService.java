package com.comssa.api.question.service.rest.major.implement;

import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion;
import com.comssa.persistence.question.repository.jpa.MajorMultipleChoiceQuestionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminMajorMultipleChoiceQuestionUpdateService {
	private final MajorMultipleChoiceQuestionJpaRepository majorMultipleChoiceQuestionJpaRepository;

	public MajorMultipleChoiceQuestion toggleCanBeShortAnswered(Long questionId) {
		MajorMultipleChoiceQuestion majorMultipleChoiceQuestion = majorMultipleChoiceQuestionJpaRepository.
			findById(questionId).get();
		majorMultipleChoiceQuestion.toggleCanBeShortAnswered();
		return majorMultipleChoiceQuestion;
	}
}
