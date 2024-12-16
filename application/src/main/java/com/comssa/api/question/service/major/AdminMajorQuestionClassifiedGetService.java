package com.comssa.api.question.service.major;


import com.comssa.persistence.question.common.domain.QuestionCategory;
import com.comssa.persistence.question.major.domain.common.MajorDescriptiveQuestion;
import com.comssa.persistence.question.major.domain.common.MajorMultipleChoiceQuestion;

import java.util.List;
import java.util.Map;

public interface AdminMajorQuestionClassifiedGetService {
	Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> getClassifiedAllMajorMultipleChoiceQuestions();

	Map<QuestionCategory, List<MajorDescriptiveQuestion>> getClassifiedAllMajorDescriptiveQuestions();

}
