package com.comssa.persistence.question.common.dto.response;

import com.comssa.persistence.question.common.domain.Question;
import com.comssa.persistence.question.common.domain.QuestionCategory;
import com.comssa.persistence.question.common.domain.QuestionChoice;
import com.comssa.persistence.question.common.domain.QuestionLevel;
import com.comssa.persistence.question.license.domain.LicenseMultipleChoiceQuestion;
import com.comssa.persistence.question.major.admin.dto.ResponseMajorMultipleChoiceQuestionForAdminDto;
import com.comssa.persistence.question.major.domain.common.MajorMultipleChoiceQuestion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
public class ResponseMultipleChoiceQuestionDto {
	private Long id;
	private String content;
	private String description;
	private String imageUrl;
	private QuestionCategory questionCategory;
	private QuestionLevel questionLevel;
	private List<ResponseQuestionChoiceDto> questionChoices;
	private boolean ifApproved;

	public static <T extends Question> ResponseMultipleChoiceQuestionDto.ResponseMultipleChoiceQuestionDtoBuilder<?, ?> common(
		T question,
		List<? extends QuestionChoice> choiceExtractor) {
		return ResponseMultipleChoiceQuestionDto.builder()
			.id(question.getId())
			.content(question.getContent())
			.questionChoices(
				choiceExtractor
					.stream()
					.map(ResponseQuestionChoiceDto::of)
					.collect(Collectors.toList())
			)
			.questionCategory(question.getQuestionCategory())
			.questionLevel(question.getQuestionLevel())
			.description(question.getDescription())
			.imageUrl(question.getImageUrl())
			.ifApproved(question.isIfApproved());
	}

	/**
	 * 유저와 관리자에 따라 다른 정적팩토리 메소드를 사용한다
	 */
	public static <T extends Question> ResponseMultipleChoiceQuestionDto forUser(
		T question,
		List<? extends QuestionChoice> choiceExtractor) {
		return common(question, choiceExtractor)
			.build();
	}

	/**
	 * 차이점은 NormalQuesiton시 주관식 가능 여부와, 허용됐는지 여부 변수의 존재 여부다
	 */
	public static ResponseMajorMultipleChoiceQuestionForAdminDto forAdmin(MajorMultipleChoiceQuestion question) {
		return ResponseMajorMultipleChoiceQuestionForAdminDto.builder()
			.id(question.getId())
			.content(question.getContent())
			.questionChoices(
				question.getQuestionChoices()
					.stream()
					.map(ResponseQuestionChoiceDto::of)
					.collect(Collectors.toList())
			)
			.questionCategory(question.getQuestionCategory())
			.questionLevel(question.getQuestionLevel())
			.description(question.getDescription())
			.imageUrl(question.getImageUrl())
			.ifApproved(question.isIfApproved())
			.canBeShortAnswered(question.isCanBeShortAnswered())
			.build();
	}

	public static ResponseMultipleChoiceQuestionDto forAdmin(LicenseMultipleChoiceQuestion question) {
		return common(question, question.getQuestionChoices())
			.build();
	}
}
