package com.server.computer_science.question.normal_question.common.service;

import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.normal_question.common.domain.NormalQuestion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface NormalQuestionClassifyService {
    public Map<QuestionCategory, List<NormalQuestion>> classifyNormalQuestionByClass(List<NormalQuestion> normalQuestions);
}
