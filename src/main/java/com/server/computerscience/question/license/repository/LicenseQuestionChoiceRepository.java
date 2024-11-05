package com.server.computerscience.question.license.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.computerscience.question.license.domain.LicenseQuestionChoice;

@Repository
public interface LicenseQuestionChoiceRepository extends JpaRepository<LicenseQuestionChoice, Long> {
}
