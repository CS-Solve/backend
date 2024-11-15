package com.study.computerscience.question.license.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class LicenseSession {

	@Enumerated(value = EnumType.STRING)
	protected LicenseCategory licenseCategory;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String content;
	@OneToMany(mappedBy = "licenseSession", cascade = CascadeType.PERSIST, orphanRemoval = false)
	private List<LicenseMultipleChoiceQuestion> licenseMultipleChoiceQuestions;

	@Builder
	public LicenseSession(String content, LicenseCategory licenseCategory) {
		this.content = content;
		this.licenseCategory = licenseCategory;
	}

	public static LicenseSession from(String content, LicenseCategory licenseCategory) {
		return LicenseSession.builder()
			.licenseCategory(licenseCategory)
			.content(content)
			.build();
	}
}
