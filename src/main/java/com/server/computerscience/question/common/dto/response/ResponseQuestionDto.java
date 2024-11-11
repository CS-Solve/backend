package com.server.computerscience.question.common.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.server.computerscience.question.common.domain.Question;
import com.server.computerscience.question.common.domain.QuestionCategory;
import com.server.computerscience.question.common.domain.QuestionChoice;
import com.server.computerscience.question.common.domain.QuestionLevel;
import com.server.computerscience.question.license.domain.LicenseMultipleChoiceQuestion;
import com.server.computerscience.question.major.admin.dto.ResponseMajorQuestionForAdminDto;
import com.server.computerscience.question.major.common.domain.MajorMultipleChoiceQuestion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@SuperBuilder
public class ResponseQuestionDto {
	private Long id;
	private String content;
	private String description;
	private String imageUrl;
	private QuestionCategory questionCategory;
	private QuestionLevel questionLevel;
	private List<ResponseQuestionChoiceDto> questionChoices;
	private boolean ifApproved;

	public ResponseQuestionDto(Long id,
		String content,
		String description,
		String imageUrl,
		QuestionCategory questionCategory,
		QuestionLevel questionLevel,
		List<ResponseQuestionChoiceDto> questionChoices,
		boolean ifApproved) {
		this.id = id;
		this.content = content;
		this.description = description;
		this.imageUrl = imageUrl;
		this.questionCategory = questionCategory;
		this.questionLevel = questionLevel;
		this.questionChoices = questionChoices;
		this.ifApproved = ifApproved;
	}

	public static <T extends Question> ResponseQuestionDto.ResponseQuestionDtoBuilder<?, ?> common(
		T question,
		List<? extends QuestionChoice> choiceExtractor) {
		return ResponseQuestionDto.builder()
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
	public static <T extends Question> ResponseQuestionDto forUser(
		T question,
		List<? extends QuestionChoice> choiceExtractor) {
		return common(question, choiceExtractor)
			.build();
	}

	/**
	 * 차이점은 NormalQuesiton시 주관식 가능 여부와, 허용됐는지 여부 변수의 존재 여부다
	 */
	public static ResponseMajorQuestionForAdminDto forAdmin(MajorMultipleChoiceQuestion question) {
		return ResponseMajorQuestionForAdminDto.builder()
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

	public static ResponseQuestionDto forAdmin(LicenseMultipleChoiceQuestion question) {
		return common(question, question.getQuestionChoices())
			.build();
	}
}
