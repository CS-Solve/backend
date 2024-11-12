package com.server.computerscience.question.major.user.service.implement;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.computerscience.question.common.service.QuestionChoiceGradeService;
import com.server.computerscience.question.major.common.domain.MajorQuestionChoice;
import com.server.computerscience.question.major.common.repository.MajorQuestionChoiceRepository;

import lombok.RequiredArgsConstructor;

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
