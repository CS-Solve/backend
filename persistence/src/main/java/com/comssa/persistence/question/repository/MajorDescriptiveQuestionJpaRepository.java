package com.comssa.persistence.question.repository;

import com.comssa.persistence.question.domain.major.MajorDescriptiveQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorDescriptiveQuestionJpaRepository extends JpaRepository<MajorDescriptiveQuestion, Long> {

}
