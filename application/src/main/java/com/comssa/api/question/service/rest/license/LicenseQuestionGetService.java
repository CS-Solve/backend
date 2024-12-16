package com.comssa.api.question.service.rest.license;


import com.comssa.persistence.question.common.domain.Question;
import com.comssa.persistence.question.common.domain.QuestionCategory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface LicenseQuestionGetService {
	Map<QuestionCategory, List<Question>> getClassifiedLicenseMultipleChoiceQuestion(
		Long sessionId);
}
