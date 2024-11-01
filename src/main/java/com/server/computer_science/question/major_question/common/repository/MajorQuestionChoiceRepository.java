package com.server.computer_science.question.major_question.common.repository;

import com.server.computer_science.question.major_question.common.domain.MajorQuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorQuestionChoiceRepository extends JpaRepository<MajorQuestionChoice, Long> {
}
