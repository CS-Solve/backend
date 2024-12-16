package com.comssa.api.question.service.common.implement;


import com.comssa.api.question.service.common.QuestionClassifyByCategoryService;
import com.comssa.persistence.question.common.domain.Question;
import com.comssa.persistence.question.common.domain.QuestionCategory;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BasicQuestionClassifyByCategoryService implements QuestionClassifyByCategoryService {
	@Override
	public Map<QuestionCategory, List<Question>> classifyQuestionByCategoryOrdered(List<? extends Question> questions) {
		return questions.stream()
			.collect(Collectors.groupingBy(
				Question::getQuestionCategory,
				LinkedHashMap::new, // 순서를 보장하는 LinkedHashMap 사용
				Collectors.toList()
			));
	}
}
