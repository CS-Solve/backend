package com.comssa.persistence.question.common.dto.response;

import com.comssa.persistence.question.major.domain.common.MajorDescriptiveQuestion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
public class ResponseDescriptiveQuestionDto extends ResponseQuestionDto {
	private String gradeStandard;

	public static ResponseDescriptiveQuestionDto forMajor(MajorDescriptiveQuestion question) {
		return ResponseDescriptiveQuestionDto.builder()
			.id(question.getId())
			.content(question.getContent())
			.questionCategory(question.getQuestionCategory())
			.questionLevel(question.getQuestionLevel())
			.description(question.getDescription())
			.imageUrl(question.getImageUrl())
			.ifMultipleChoice(false)
			.build();
	}

	public static ResponseDescriptiveQuestionDto forAdminMajor(MajorDescriptiveQuestion question) {
		return ResponseDescriptiveQuestionDto.builder()
			.id(question.getId())
			.content(question.getContent())
			.questionCategory(question.getQuestionCategory())
			.questionLevel(question.getQuestionLevel())
			.description(question.getDescription())
			.imageUrl(question.getImageUrl())
			.ifMultipleChoice(false)
			.ifApproved(question.isIfApproved())
			.gradeStandard(question.getGradeStandard())
			.build();
	}
}
