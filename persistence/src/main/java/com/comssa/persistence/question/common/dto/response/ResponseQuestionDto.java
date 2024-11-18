package com.comssa.persistence.question.common.dto.response;

import com.comssa.persistence.question.common.domain.Question;
import com.comssa.persistence.question.common.domain.QuestionCategory;
import com.comssa.persistence.question.common.domain.QuestionLevel;
import com.comssa.persistence.question.license.domain.LicenseMultipleChoiceQuestion;
import com.comssa.persistence.question.major.domain.common.MajorMultipleChoiceQuestion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
public class ResponseQuestionDto {
	private Long id;
	private String content;
	private String description;
	private String imageUrl;
	private QuestionCategory questionCategory;
	private QuestionLevel questionLevel;
	private boolean ifApproved;
	private boolean ifMultipleChoice;

	public static <T extends Question> ResponseMultipleChoiceQuestionDto basic(
		T question) {

		if (question instanceof LicenseMultipleChoiceQuestion) {
			return ResponseMultipleChoiceQuestionDto.forLicense((LicenseMultipleChoiceQuestion) question);
		}
		if (question instanceof MajorMultipleChoiceQuestion) {
			return ResponseMultipleChoiceQuestionDto.forMajor((MajorMultipleChoiceQuestion) question);
		}
		return ResponseMultipleChoiceQuestionDto.builder()
			.id(question.getId())
			.content(question.getContent())
			.questionCategory(question.getQuestionCategory())
			.questionLevel(question.getQuestionLevel())
			.description(question.getDescription())
			.imageUrl(question.getImageUrl())
			.build();
	}

	/**
	 * 유저와 관리자에 따라 다른 정적팩토리 메소드를 사용한다
	 */
	public static <T extends Question> ResponseMultipleChoiceQuestionDto forUser(
		T question) {
		return basic(question);
	}
}
