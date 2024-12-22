package com.comssa.persistence.question.repository.jpa;

import com.comssa.persistence.question.domain.common.QuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionChoiceRepository extends JpaRepository<QuestionChoice, Long> {
}
