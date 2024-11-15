package com.server.computerscience.question.major.common.service;

import com.server.computerscience.question.common.domain.Question;
import com.server.computerscience.question.common.domain.QuestionCategory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface QuestionClassifyByCategoryService {
    <T extends Question> Map<QuestionCategory, List<T>> classifyQuestionByCategoryOrdered(List<T> questions);
}
