package com.comssa.api.question.service.common.implement;

import com.comssa.persistence.question.common.domain.QuestionChoice;
import com.comssa.persistence.question.common.repository.QuestionChoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionChoiceGradeService implements com.comssa.api.question.service.common.QuestionChoiceGradeService {
	private final QuestionChoiceRepository questionChoiceRepository;

	@Override
	public QuestionChoice findById(Long id) {
		return questionChoiceRepository.findById(id).orElse(null);
	}
}
