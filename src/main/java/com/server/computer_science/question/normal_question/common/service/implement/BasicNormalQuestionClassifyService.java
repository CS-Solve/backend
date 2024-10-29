package com.server.computer_science.question.normal_question.common.service.implement;

import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.normal_question.common.domain.NormalQuestion;
import com.server.computer_science.question.normal_question.common.service.NormalQuestionClassifyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BasicNormalQuestionClassifyService implements NormalQuestionClassifyService {

    @Override
    public Map<QuestionCategory, List<NormalQuestion>> classifyNormalQuestionByClass(List<NormalQuestion> normalQuestions) {
        //        // 요청된 모든 카테고리에 대해 문제가 없어도 빈 리스트 보장
//        requestGetNormalQuestionsDto.getQuestionCategories().forEach(category ->
//                categoryMap.putIfAbsent(category, new ArrayList<>())
//
        return normalQuestions.stream().collect(Collectors.groupingBy(NormalQuestion::getQuestionCategory));
    }
}
