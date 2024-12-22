package com.comssa.persistence.question.repository;

import com.comssa.persistence.question.domain.major.MajorDescriptiveQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorDescriptiveQuestionRepository extends JpaRepository<MajorDescriptiveQuestion, Long> {

	@Query("SELECT DISTINCT md FROM MajorDescriptiveQuestion md "
		+ "ORDER BY md.ifApproved")
	List<MajorDescriptiveQuestion> findAllSortedByIfApproved();
}
