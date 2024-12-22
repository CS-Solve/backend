package com.comssa.persistence.question.repository.jpa;


import com.comssa.persistence.question.domain.common.Question;

import java.util.Optional;

public interface QuestionCustomRepository<T extends Question> {
	Optional<T> findByIdFetchCommentsOrderByCreatedAtDesc(Long questionId);
}
