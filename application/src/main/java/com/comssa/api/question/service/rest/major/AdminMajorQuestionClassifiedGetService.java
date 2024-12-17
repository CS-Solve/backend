package com.comssa.api.question.service.rest.major;


import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionCategory;

import java.util.List;
import java.util.Map;

public interface AdminMajorQuestionClassifiedGetService {
    Map<QuestionCategory, List<Question>> getClassifiedAllMajorMultipleChoiceQuestions();

    Map<QuestionCategory, List<Question>> getClassifiedAllMajorDescriptiveQuestions();
}
