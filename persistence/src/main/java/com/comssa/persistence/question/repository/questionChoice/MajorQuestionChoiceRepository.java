package com.comssa.persistence.question.repository.questionChoice;


import com.comssa.persistence.question.domain.major.MajorQuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorQuestionChoiceRepository extends JpaRepository<MajorQuestionChoice, Long> {
}
