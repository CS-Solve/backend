package com.comssa.api.question.service.view;

import com.comssa.api.question.domain.HtmlTag;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.license.LicenseCategory;
import com.comssa.persistence.question.domain.license.LicenseSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HtmlTagService {

	private static String getAllLicenseCategories() {
		return Arrays.stream(LicenseCategory.values())
			.map(LicenseCategory::getKorean)
			.collect(Collectors.joining(", "));
	}

	public void addTagToModel(HtmlTag tag, Model model) {
		model.addAttribute("title", tag.getTitle());
		model.addAttribute("description", tag.getDescription());
		model.addAttribute("questionSession", tag.getQuestionSession());
	}

	public void forLicenseMain(Model model) {
		String title = getAllLicenseCategories() + "기출";

		String description = getAllLicenseCategories()
			+ " 등 컴퓨터 사이언스(CS) 자격증 기출 문제를 풀어보세요. 기출 문제 풀이를 통해 자격증 대비가 가능합니다.";

		String questionSession = title;


		addTagToModel(HtmlTag.from(
				title,
				questionSession,
				description
			),
			model);
	}

	public void forLicenseQuestion(LicenseSession licenseSession, Model model) {


		LocalDateTime twoYearsAgo = LocalDateTime.now().minusYears(2); // 현재 시간에서 2년 전
		boolean recent = licenseSession.getCreatedAt().isAfter(twoYearsAgo);

		String title = "[" + licenseSession.getLicenseCategory().getKorean() + "] ";
		if (recent) {
			title += "최신 ";
		}
		title += "기출 문제 - " + licenseSession.getContent();


		String categories = getAllLicenseCategories();
		String description = categories + " 등 컴퓨터 사이언스(CS) 자격증 기출 문제를 풀어보세요. 기출 문제 풀이를 통해 자격증 대비가 가능합니다.";

		String questionSession = licenseSession.getLicenseCategory().getKorean()
			+ " - "
			+ licenseSession.getContent()
			+ " / 실제 시험은 더 어려울 수 있으니, 문제의 중심 개념 (정답 선택지) 위주로 학습 추천";

		addTagToModel(HtmlTag.from(
				title,
				questionSession,
				description
			),
			model);
	}

	public void forMajor(Set<QuestionCategory> questionCategories, boolean isMultipleChoice, Model model) {
		if (isMultipleChoice) {
			addTagToModel(forMajorMultiple(questionCategories), model);
			return;
		}
		addTagToModel(forMajorDescriptive(questionCategories), model);
	}

	public HtmlTag forMajorMultiple(Set<QuestionCategory> questionCategories) {
		String title = "CS 전공 문제 - " + questionCategories.stream()
			.map(QuestionCategory::getKorean)
			.collect(Collectors.joining(", "));

		String description = "다양한 분야와 난이도의 CS (컴퓨터 사이언스) 문제를 풀어볼 수 있습니다.";

		String questionSession = title;

		return HtmlTag.from(
			title,
			questionSession,
			description
		);
	}

	public HtmlTag forMajorDescriptive(Set<QuestionCategory> questionCategories) {
		String title = "CS 가상 기술 면접 - " + questionCategories.stream()
			.map(QuestionCategory::getKorean)
			.collect(Collectors.joining(", "));

		String description = "다양한 분야의 CS 기술 면접 질문들이 준비되어있습니다. AI 기반 채점을 통해 기술 면접에 대비할 수 있습니다.";

		String questionSession = title;


		return HtmlTag.from(
			title,
			questionSession,
			description
		);
	}

}
