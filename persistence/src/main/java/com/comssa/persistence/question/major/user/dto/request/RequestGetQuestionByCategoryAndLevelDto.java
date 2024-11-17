package com.comssa.persistence.question.major.user.dto.request;

import com.comssa.persistence.question.common.domain.QuestionCategory;
import com.comssa.persistence.question.common.domain.QuestionLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class RequestGetQuestionByCategoryAndLevelDto {
	private List<QuestionCategory> questionCategories;
	private List<QuestionLevel> questionLevels;

	public static RequestGetQuestionByCategoryAndLevelDto from(
		List<QuestionCategory> questionCategories,
		List<QuestionLevel> questionLevels) {
		return RequestGetQuestionByCategoryAndLevelDto.builder()
			.questionCategories(questionCategories)
			.questionLevels(questionLevels)
			.build();
	}

	/**
	 * @param questionCategories
	 * @param questionLevels     한글로 된 문제 카테고리와 레벨을 받고 Enum으로 전환한 후에 반환.
	 */
	public static RequestGetQuestionByCategoryAndLevelDto fromKorean(
		List<String> questionCategories,
		List<String> questionLevels) {
		List<QuestionCategory> categories = mapOrGetAllEnumValues(questionCategories, QuestionCategory.class,
			QuestionCategory::getKorean);
		List<QuestionLevel> levels = mapOrGetAllEnumValues(questionLevels, QuestionLevel.class,
			QuestionLevel::getKorean);
		// DTO 빌드
		return RequestGetQuestionByCategoryAndLevelDto.builder()
			.questionCategories(categories)
			.questionLevels(levels)
			.build();
	}

	/*
	리스트에 아무 것도 포함되어있지 않다면, Enum의 모든 값을 포함하여 반환한다.
	 */
	private static <T extends Enum<T>> List<T> mapOrGetAllEnumValues(
		List<String> values, Class<T> enumClass,
		Function<T, String> getKorean) {
		if (values == null || values.isEmpty()) {
			return Arrays.asList(enumClass.getEnumConstants());
		}
		return values.stream()
			.map(value -> Arrays.stream(enumClass.getEnumConstants())
				.filter(e -> getKorean.apply(e).equals(value))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Invalid " + enumClass.getSimpleName() + ": " + value)))
			.collect(Collectors.toList());
	}
}
