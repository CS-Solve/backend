package com.server.computer_science.question.license_question.repository;

import com.server.computer_science.question.license_question.domain.LicenseMultipleChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicenseMultipleChoiceQuestionRepository extends JpaRepository<LicenseMultipleChoiceQuestion, Long> {

    @Query("SELECT DISTINCT lnq FROM LicenseMultipleChoiceQuestion  lnq " +
            "LEFT JOIN FETCH lnq.questionChoices " +
            "WHERE lnq.licenseSession.id = :licenseSessionId ")
    List<LicenseMultipleChoiceQuestion> findAllByLicenseSessionIdFetchChoices(@Param("licenseSessionId") Long licenseSessionId);
}
