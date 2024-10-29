package com.server.computer_science.question.license_question.repository;

import com.server.computer_science.question.license_question.domain.LicenseSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LicenseSessionRepository extends JpaRepository<LicenseSession, Long> {

    @Query("SELECT ls FROM LicenseSession ls "+"WHERE ls.content = :session")
    Optional<LicenseSession> findLicenseSessionByContent(@Param("session") String content);
}
