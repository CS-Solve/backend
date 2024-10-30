package com.server.computer_science.question.license_question.repository;

import com.server.computer_science.question.license_question.domain.LicenseNormalQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseNormalQuestionRepository extends JpaRepository<LicenseNormalQuestion, Long> {
}
