package com.comssa.persistence.question.repository;

import com.comssa.persistence.question.domain.common.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	@Query("SELECT DISTINCT q "
		+ "FROM Question q "
		+ "LEFT JOIN FETCH q.comments c "
		+ "WHERE q.id = :questionId "
		+ "ORDER BY c.createdAt DESC")
	Optional<Question> findByIdFetchCommentsOrderByCreatedAtDesc(@Param("questionId") Long questionId);
}
