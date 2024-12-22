package com.comssa.persistence.question.repository;


import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.license.LicenseMultipleChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicenseMultipleChoiceQuestionRepository extends JpaRepository<LicenseMultipleChoiceQuestion, Long> {

	@Query("SELECT DISTINCT lnq FROM LicenseMultipleChoiceQuestion  lnq "
		+ "LEFT JOIN FETCH lnq.questionChoices "
		+ "WHERE lnq.licenseSession.id = :licenseSessionId "
		+ "ORDER BY lnq.ifApproved")
	List<LicenseMultipleChoiceQuestion> findAllByLicenseSessionIdFetchChoicesOrderByApproved(
		@Param("licenseSessionId") Long licenseSessionId);

	@Query("SELECT DISTINCT lnq FROM LicenseMultipleChoiceQuestion  lnq "
		+ "LEFT JOIN FETCH lnq.questionChoices "
		+ "WHERE lnq.licenseSession.id = :licenseSessionId "
		+ "AND lnq.ifApproved = true")
	List<LicenseMultipleChoiceQuestion> findAllByLicenseSessionIdFetchChoicesAndIfApproved(
		@Param("licenseSessionId") Long licenseSessionId);


	@Query("SELECT DISTINCT lnq FROM LicenseMultipleChoiceQuestion  lnq "
		+ "LEFT JOIN FETCH lnq.questionChoices "
		+ "WHERE lnq.questionCategory IN :questionCategories ")
	List<LicenseMultipleChoiceQuestion> findAllByQuestionCategoriesFetchChoices(
		@Param("questionCategories") List<QuestionCategory> questionCategories);

}
