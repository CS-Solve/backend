package com.server.computer_science.question.common.service.Implements;

import com.server.computer_science.question.common.domain.Question;
import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.major_question.common.service.QuestionClassifyByCategoryService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BasicQuestionClassifyByCategoryService implements QuestionClassifyByCategoryService {
    @Override
    public <T extends Question> Map<QuestionCategory, List<T>> classifyQuestionByCategoryOrdered(List<T> questions) {
        return questions.stream()
                .collect(Collectors.groupingBy(
                        Question::getQuestionCategory,
                        LinkedHashMap::new, // 순서를 보장하는 LinkedHashMap 사용
                        Collectors.toList()
                ));
    }
}
