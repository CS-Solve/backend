package com.comssa.persistence.question.repository.jpa;


import com.comssa.persistence.question.domain.license.LicenseQuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseQuestionChoiceRepository extends JpaRepository<LicenseQuestionChoice, Long> {
}
