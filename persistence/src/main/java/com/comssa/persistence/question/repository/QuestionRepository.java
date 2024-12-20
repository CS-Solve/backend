package com.comssa.persistence.question.repository;

import com.comssa.persistence.question.domain.common.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>, QuestionCustomRepository {

}
