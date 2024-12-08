package com.comssa.api.question.major.user.service.implement;

import com.comssa.api.question.common.service.QuestionChoiceGradeService;
import com.comssa.persistence.question.major.domain.common.MajorQuestionChoice;
import com.comssa.persistence.question.major.repository.MajorQuestionChoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MajorQuestionChoiceGradeService implements QuestionChoiceGradeService<MajorQuestionChoice> {
	private final MajorQuestionChoiceRepository majorQuestionChoiceRepository;

	@Override
	public MajorQuestionChoice findById(Long id) {
		return majorQuestionChoiceRepository.findById(id).orElse(null);
	}
}
