package com.comssa.persistence.question.license.domain;

import com.comssa.persistence.question.common.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class LicenseSession extends BaseEntity {

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
