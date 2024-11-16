package com.persistence.computerscience.question.major.repository;


import com.persistence.computerscience.question.major.domain.common.MajorQuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorQuestionChoiceRepository extends JpaRepository<MajorQuestionChoice, Long> {
}
