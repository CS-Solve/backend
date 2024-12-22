package com.comssa.api.question.service.rest.common.implement;

import com.comssa.persistence.question.domain.common.QuestionChoice;
import com.comssa.persistence.question.repository.questionChoice.QuestionChoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionChoiceGradeService
	implements com.comssa.api.question.service.rest.common.QuestionChoiceGradeService {
	private final QuestionChoiceRepository questionChoiceRepository;

	@Override
	public QuestionChoice findById(Long id) {
		return questionChoiceRepository.findById(id).orElse(null);
	}
}
