package com.api.computerscience.question.common.service.implement;


import com.api.computerscience.question.major.common.service.QuestionClassifyByCategoryService;
import com.persistence.computerscience.question.common.domain.Question;
import com.persistence.computerscience.question.common.domain.QuestionCategory;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BasicQuestionClassifyByCategoryService implements QuestionClassifyByCategoryService {
	@Override
	public <T extends Question> Map<QuestionCategory, List<T>> classifyQuestionByCategoryOrdered(List<T> questions) {
		return questions.stream()
			.collect(Collectors.groupingBy(
				Question::getQuestionCategory,
				LinkedHashMap::new, // 순서를 보장하는 LinkedHashMap 사용
				Collectors.toList()
			));
	}
}
