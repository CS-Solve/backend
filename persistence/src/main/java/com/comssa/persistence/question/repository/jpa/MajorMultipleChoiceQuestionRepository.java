package com.comssa.persistence.question.repository.jpa;


import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorMultipleChoiceQuestionRepository extends JpaRepository<MajorMultipleChoiceQuestion, Long> {

}
