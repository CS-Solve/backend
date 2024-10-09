package com.server.computer_science.question.normal_question.service;

import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.normal_question.domain.NormalQuestion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface NormalQuestionClassifyService {
    public Map<QuestionCategory, List<NormalQuestion>> classifyNormalQuestionByClass(List<NormalQuestion> normalQuestions);
}
