package com.comssa.persistence.question.repository;


import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorMultipleChoiceQuestionJpaRepository extends JpaRepository<MajorMultipleChoiceQuestion, Long> {

}
