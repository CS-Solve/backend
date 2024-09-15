package com.server.computer_science.question.normal_question.repository;

import com.server.computer_science.question.normal_question.domain.NormalQuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NormalQuestionChoiceRepository extends JpaRepository<NormalQuestionChoice, Long> {
}
