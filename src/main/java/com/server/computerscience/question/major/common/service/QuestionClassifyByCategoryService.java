package com.server.computerscience.question.major.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.server.computerscience.question.common.domain.Question;
import com.server.computerscience.question.common.domain.QuestionCategory;

@Service
public interface QuestionClassifyByCategoryService {
	<T extends Question> Map<QuestionCategory, List<T>> classifyQuestionByCategoryOrdered(List<T> questions);
}
