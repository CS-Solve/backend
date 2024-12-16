package com.comssa.api.question.common.controller;


import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionServiceFactory {
	private final List<Object> questionService;

	public QuestionServiceFactory(ApplicationContext applicationContext) {
		this.questionService =
			applicationContext.getBeansOfType(Object.class).values()
				.stream()
				.filter(
					bean -> {
						String name = bean.getClass().getSimpleName().toLowerCase();
						return name.contains("question") && name.contains("service");
					}
				)
				.collect(Collectors.toList());
	}

	public <T> T getQuestionService(
		String questionField,
		String questionType,
		String questionAct,
		Class<T> classType
	) {
		return findServiceByClassName(questionField, questionType, questionAct, classType, false);
	}

	public <T> T getAdminQuestionService(
		String questionField,
		String questionType,
		String questionAct,
		Class<T> classType
	) {
		return findServiceByClassName(questionField, questionType, questionAct, classType, true);
	}

	private <T> T findServiceByClassName(
		String questionField,
		String questionType,
		String questionAct,
		Class<T> classType,
		boolean isAdmin
	) {
		return questionService.stream()
			.filter(service -> {
				String name = service.getClass().getSimpleName().toLowerCase();
				boolean matches = name.contains(questionField.toLowerCase())
					&& name.contains(questionType.toLowerCase())
					&& name.contains(questionAct.toLowerCase());
				if (isAdmin) {
					matches = matches && name.contains("admin");
				}
				return matches;
			})
			.map(classType::cast)
			/*
			이 부분으로 반환값을 Object대신 제네릭을 사용함에도 ClassCastException이 여전히 발생할 수 있지만,
			제네릭을 사용함으로 에러 처리를 호출부가 아니라 현 클래스에서 집중할 수 있음
			 */
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(
				"No service found for questionField: " + questionField
					+ ", questionType: " + questionType
					+ ", questionAct: " + questionAct
			));
	}
}
