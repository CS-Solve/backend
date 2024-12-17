package com.comssa.api.question.service.rest.common;


import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface QuestionClassifyByCategoryService {
    Map<QuestionCategory, List<Question>> classifyQuestionByCategoryOrdered(List<? extends Question> questions);
}

