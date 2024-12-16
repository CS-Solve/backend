package com.comssa.api.question.service.common;


import com.comssa.persistence.question.common.domain.Question;
import com.comssa.persistence.question.common.domain.QuestionCategory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface QuestionClassifyByCategoryService {
	Map<QuestionCategory, List<Question>> classifyQuestionByCategoryOrdered(List<? extends Question> questions);
}

