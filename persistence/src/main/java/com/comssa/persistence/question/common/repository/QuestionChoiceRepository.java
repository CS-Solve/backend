package com.comssa.persistence.question.common.repository;

import com.comssa.persistence.question.common.domain.QuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionChoiceRepository extends JpaRepository<QuestionChoice, Long> {
}
