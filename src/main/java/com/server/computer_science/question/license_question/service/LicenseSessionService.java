package com.server.computer_science.question.license_question.service;


import com.server.computer_science.question.license_question.domain.LicenseCategory;
import com.server.computer_science.question.license_question.domain.LicenseSession;
import com.server.computer_science.question.license_question.repository.LicenseSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LicenseSessionService {
    private final LicenseSessionRepository licenseSessionRepository;

    public LicenseSession getLicenseSession(String session, LicenseCategory licenseCategory){
        Optional<LicenseSession> licenseSession = licenseSessionRepository.findLicenseSessionByContent(session);
        if(licenseSession.isPresent()){
            return licenseSession.get();
        }
        LicenseSession newLicenseSession = LicenseSession.from(session,licenseCategory);
        licenseSessionRepository.save(newLicenseSession);
        return newLicenseSession;
    }
}
