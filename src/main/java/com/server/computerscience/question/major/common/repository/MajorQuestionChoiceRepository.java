package com.server.computerscience.question.major.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.computerscience.question.major.common.domain.MajorQuestionChoice;

@Repository
public interface MajorQuestionChoiceRepository extends JpaRepository<MajorQuestionChoice, Long> {
}
