package com.server.computer_science.question.license_question.repository;

import com.server.computer_science.question.license_question.domain.LicenseNormalQuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseNormalQuestionChoiceRepository extends JpaRepository<LicenseNormalQuestionChoice, Long> {
}
