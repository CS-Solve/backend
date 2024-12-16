package com.comssa.api.question.common.controller;


import com.comssa.api.question.common.service.MultipleChoiceQuestionGradeService;
import com.comssa.persistence.question.common.domain.QuestionChoice;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Lazy
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

	public MultipleChoiceQuestionGradeService<? extends QuestionChoice> getMultipleChoiceQuestionGradeService(
		String questionClass,
		String questionType,
		String questionAct
	) {
		return (MultipleChoiceQuestionGradeService<? extends QuestionChoice>) questionService.stream()
			.filter(service -> {
				String name = service.getClass().getSimpleName().toLowerCase();
				return name.contains(questionClass.toLowerCase())
					&& name.contains(questionType.toLowerCase())
					&& name.contains(questionAct.toLowerCase());
			})
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(
				"No service found for questionClass: " + questionClass
					+ ", questionType: " + questionType
					+ ", questionAct: " + questionAct
			));
	}
}
