package com.comssa.persistence.question.repository;


import com.comssa.persistence.question.domain.common.Question;

import java.util.Optional;

public interface QuestionCustomRepository {
	Optional<Question> findByIdFetchCommentsOrderByCreatedAtDesc(Long questionId);
}
