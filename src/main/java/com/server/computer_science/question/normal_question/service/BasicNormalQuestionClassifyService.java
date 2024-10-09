package com.server.computer_science.question.normal_question.service;

import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.normal_question.domain.NormalQuestion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BasicNormalQuestionClassifyService implements NormalQuestionClassifyService {

    @Override
    public Map<QuestionCategory, List<NormalQuestion>> classifyNormalQuestionByClass(List<NormalQuestion> normalQuestions) {
        return normalQuestions.stream().collect(Collectors.groupingBy(NormalQuestion::getQuestionCategory));
    }
}
