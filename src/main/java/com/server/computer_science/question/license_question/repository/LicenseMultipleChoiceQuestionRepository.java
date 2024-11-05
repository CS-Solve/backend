package com.server.computer_science.question.license_question.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.server.computer_science.question.license_question.domain.LicenseMultipleChoiceQuestion;

@Repository
public interface LicenseMultipleChoiceQuestionRepository extends JpaRepository<LicenseMultipleChoiceQuestion, Long> {

	@Query("SELECT DISTINCT lnq FROM LicenseMultipleChoiceQuestion  lnq " +
		"LEFT JOIN FETCH lnq.questionChoices " +
		"WHERE lnq.licenseSession.id = :licenseSessionId ")
	List<LicenseMultipleChoiceQuestion> findAllByLicenseSessionIdFetchChoices(
		@Param("licenseSessionId") Long licenseSessionId);

	@Query("SELECT lnq FROM LicenseMultipleChoiceQuestion  lnq " +
		"LEFT JOIN FETCH lnq.questionChoices " +
		"WHERE lnq.id = :questionId ")
	Optional<LicenseMultipleChoiceQuestion> findByIdFetchChoices(@Param("questionId") Long questionId);
}
