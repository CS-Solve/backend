package com.server.computerscience.question.major.common.repository;

import com.server.computerscience.question.major.common.domain.MajorQuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorQuestionChoiceRepository extends JpaRepository<MajorQuestionChoice, Long> {
}
