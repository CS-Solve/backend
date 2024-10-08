package com.server.computer_science.question.normal_question.service;

import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.common.QuestionLevel;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BasicQuestionSelectorService implements QuestionSelectorService {

    @Override
    public List<String> getCategories() {
        return Arrays.stream(QuestionCategory.values())
                .filter(QuestionCategory::isCanBeShow)
                .map(QuestionCategory::getKorean)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getLevels() {
        return Arrays.stream(QuestionLevel.values())
                .map(QuestionLevel::getKorean)
                .collect(Collectors.toList());
    }
}
