package com.comssa.persistence.question.dto.major.request

import com.comssa.persistence.question.domain.common.QuestionCategory
import com.comssa.persistence.question.domain.common.QuestionLevel

data class RequestGetQuestionByCategoryAndLevelDto(
	val questionLevels: List<QuestionLevel>,
	val questionCategories: List<QuestionCategory>,
) {
	companion object {
		@JvmStatic
		fun fromKorean(
			questionCategories: List<String>?,
			questionLevels: List<String>?,
		): RequestGetQuestionByCategoryAndLevelDto =
			RequestGetQuestionByCategoryAndLevelDto(
				questionCategories =
					mapOrGetAllEnumValues(
						questionCategories,
						QuestionCategory::class.java,
						QuestionCategory::getKorean,
					),
				questionLevels =
					mapOrGetAllEnumValues(
						questionLevels,
						QuestionLevel::class.java,
						QuestionLevel::getKorean,
					),
			)

		private fun <T : Enum<T>> mapOrGetAllEnumValues(
			values: List<String>?,
			enumClass: Class<T>,
			getKorean: (T) -> String,
			/*
			Enum(T)을 입력받고,
			->는 함수 임을 의미
			String을 반환
			Java의. Function<T,String> 과 동일
			 */
		): List<T> {
			if (values.isNullOrEmpty()) {
				return enumClass.enumConstants.toList()
			}
			return values.map { value ->
				enumClass.enumConstants.firstOrNull { getKorean(it) == value }
					?: throw IllegalArgumentException("Unknown enum value: $value")
			}
		}
	}
}
