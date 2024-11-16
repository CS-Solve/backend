package com.persistence.computerscience.question.license.repository;


import com.persistence.computerscience.question.license.domain.LicenseQuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseQuestionChoiceRepository extends JpaRepository<LicenseQuestionChoice, Long> {
}
