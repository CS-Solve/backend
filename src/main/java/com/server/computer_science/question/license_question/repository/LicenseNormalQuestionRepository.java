package com.server.computer_science.question.license_question.repository;

import com.server.computer_science.question.license_question.domain.LicenseNormalQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicenseNormalQuestionRepository extends JpaRepository<LicenseNormalQuestion, Long> {

    @Query("SELECT DISTINCT lnq FROM LicenseNormalQuestion  lnq " +
            "LEFT JOIN FETCH lnq.normalQuestionChoices " +
            "WHERE lnq.licenseSession.id = :licenseSessionId ")
    List<LicenseNormalQuestion> findAllByLicenseSessionIdFetchChoices(@Param("licenseSessionId") Long licenseSessionId);
}
