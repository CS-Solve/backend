package com.server.computerscience.question.license.domain;

import java.util.Arrays;
import java.util.Optional;

public enum LicenseCategory {
	SQLD("SQLD"),
	ENGINEER("정보처리기사"),
	ADSP("ADSP"),
	AWS_SAA("AWS-SAA");

	private final String korean;

	LicenseCategory(String korean) {
		this.korean = korean;
	}

	public static Optional<LicenseCategory> fromKorean(String korean) {
		return Arrays.stream(LicenseCategory.values())
			.filter(category -> category.getKorean().equals(korean))
			.findFirst();
	}

	public String getKorean() {
		return korean;
	}

}
