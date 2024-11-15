package com.study.computerscience.question.major.user.service.implement;

import com.study.computerscience.question.common.service.QuestionChoiceGradeService;
import com.study.computerscience.question.major.common.domain.MajorQuestionChoice;
import com.study.computerscience.question.major.common.repository.MajorQuestionChoiceRepository;
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
