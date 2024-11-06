package com.server.computerscience.question.license.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.computerscience.question.license.domain.LicenseCategory;
import com.server.computerscience.question.license.domain.LicenseSession;
import com.server.computerscience.question.license.repository.LicenseSessionRepository;

import lombok.RequiredArgsConstructor;

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