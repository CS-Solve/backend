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
					bean ->
					{
						String name = bean.getClass().getSimpleName().toLowerCase();
						return name.contains("question") && name.contains("service");
					}
				)
				.collect(Collectors.toList());
	}

	public <T> T getMultipleChoiceQuestionGradeService(
		String questionClass,
		String questionType,
		String questionAct,
		Class<T> classType
	) {
		return questionService.stream()
			.filter(service -> {
				String name = service.getClass().getSimpleName().toLowerCase();
				return name.contains(questionClass.toLowerCase())
					&& name.contains(questionType.toLowerCase())
					&& name.contains(questionAct.toLowerCase());
			})
			.map(classType::cast)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(
				"No service found for questionClass: " + questionClass
					+ ", questionType: " + questionType
					+ ", questionAct: " + questionAct
			));
	}
}
