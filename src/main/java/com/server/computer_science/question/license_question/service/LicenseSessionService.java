package com.server.computer_science.question.license_question.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.computer_science.question.license_question.domain.LicenseCategory;
import com.server.computer_science.question.license_question.domain.LicenseSession;
import com.server.computer_science.question.license_question.repository.LicenseSessionRepository;

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
