package com.comssa.api.question.service.rest.license;


import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface LicenseQuestionGetService {
	Map<QuestionCategory, List<Question>> getClassifiedLicenseMultipleChoiceQuestion(
		Long sessionId);
}
