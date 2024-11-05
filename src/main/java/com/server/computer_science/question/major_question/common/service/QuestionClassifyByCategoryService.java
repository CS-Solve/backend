package com.server.computer_science.question.major_question.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.server.computer_science.question.common.domain.Question;
import com.server.computer_science.question.common.domain.QuestionCategory;

@Service
public interface QuestionClassifyByCategoryService {
	<T extends Question> Map<QuestionCategory, List<T>> classifyQuestionByCategoryOrdered(List<T> questions);
}
