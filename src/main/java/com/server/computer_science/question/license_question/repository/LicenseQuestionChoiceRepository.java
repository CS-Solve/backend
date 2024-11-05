package com.server.computer_science.question.license_question.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.computer_science.question.license_question.domain.LicenseQuestionChoice;

@Repository
public interface LicenseQuestionChoiceRepository extends JpaRepository<LicenseQuestionChoice, Long> {
}
