package com.comssa.api.question.service.rest.license.implement;


import com.comssa.persistence.question.domain.license.LicenseCategory;
import com.comssa.persistence.question.domain.license.LicenseSession;
import com.comssa.persistence.question.repository.jpa.LicenseSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LicenseSessionService {
	private final LicenseSessionRepository licenseSessionRepository;

	public LicenseSession getLicenseSession(String session, LicenseCategory licenseCategory) {
		Optional<LicenseSession> licenseSession = licenseSessionRepository.findLicenseSessionByContent(session,
			licenseCategory);
		if (licenseSession.isPresent()) {
			return licenseSession.get();
		}
		LicenseSession newLicenseSession = LicenseSession.from(session, licenseCategory);
		licenseSessionRepository.save(newLicenseSession);
		return newLicenseSession;
	}

	public LicenseSession getLicenseSessionById(Long licenseSessionId) {
		return licenseSessionRepository.findById(licenseSessionId).orElse(null);
	}
}
