package com.comssa.persistence.question.common.dto.response;

import com.comssa.persistence.question.common.domain.Question;
import com.comssa.persistence.question.common.domain.QuestionCategory;
import com.comssa.persistence.question.common.domain.QuestionLevel;
import com.comssa.persistence.question.license.domain.LicenseMultipleChoiceQuestion;
import com.comssa.persistence.question.major.domain.common.MajorDescriptiveQuestion;
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

	public static ResponseQuestionDto from(Question question) {
		if (question instanceof LicenseMultipleChoiceQuestion) {
			return ResponseMultipleChoiceQuestionDto.forLicense((LicenseMultipleChoiceQuestion) question);
		}
		if (question instanceof MajorMultipleChoiceQuestion) {
			return ResponseMultipleChoiceQuestionDto.forMajor((MajorMultipleChoiceQuestion) question);
		}
		if (question instanceof MajorDescriptiveQuestion) {
			return ResponseQuestionDto.builder()
				.id(question.getId())
				.content(question.getContent())
				.questionCategory(question.getQuestionCategory())
				.questionLevel(question.getQuestionLevel())
				.description(question.getDescription())
				.imageUrl(question.getImageUrl())
				.build();
		}
		throw new IllegalArgumentException("Unsupported question type: " + question.getClass().getName());
	}
//	/**
//	 * 유저와 관리자 또는 문제 종류에 따라 다른 정적팩토리 메소드를 사용한다
//	 */
//	public static <T extends Question> ResponseMultipleChoiceQuestionDto multipleQuestionForUser(
//		T question) {
//		return multipleQuestion(question);
//	}
}
