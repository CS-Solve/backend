package com.comssa.api.question.controller.rest.common

import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import java.util.Locale

@Component
class QuestionServiceFactory(
	applicationContext: ApplicationContext,
) {
	val questionService: List<Any> =
		applicationContext
			.getBeansOfType(Any::class.java)
			.values
			.filter { bean ->
				val name = bean::class.java.simpleName.lowercase()
				name.contains("question") && name.contains("service")
			}.toList()

	fun <T> getQuestionService(
		questionField: String,
		questionType: String,
		questionAct: String,
		classType: Class<T>,
	): T = findServiceByClassName(questionField, questionType, questionAct, classType, false)

	private fun <T> findServiceByClassName(
		questionField: String,
		questionType: String,
		questionAct: String,
		classType: Class<T>,
		isAdmin: Boolean,
	): T =
		questionService
			.stream()
			.filter { service: Any ->
				val name = service.javaClass.simpleName.lowercase(Locale.getDefault())
				var matches =
					name.contains(questionField.lowercase(Locale.getDefault())) &&
						name.contains(questionType.lowercase(Locale.getDefault())) &&
						name.contains(questionAct.lowercase(Locale.getDefault()))
				if (isAdmin) {
					matches = matches && name.contains("admin")
				}
				matches
			}.map { obj: Any? -> classType.cast(obj) }
			/*
				이 부분으로 반환값을 Object대신 제네릭을 사용함에도 ClassCastException이 여전히 발생할 수 있지만,
			     네릭을 사용함으로 에러 처리를 호출부가 아니라 현 클래스에서 집중할 수 있음
			 */
			.findFirst()
			.orElseThrow {
				IllegalArgumentException(
					(
						"No service found for questionField: " + questionField +
							", questionType: " + questionType +
							", questionAct: " + questionAct
					),
				)
			}
}
