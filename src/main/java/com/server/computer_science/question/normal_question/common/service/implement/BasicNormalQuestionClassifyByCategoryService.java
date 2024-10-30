package com.server.computer_science.question.normal_question.common.service.implement;

import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.license_question.domain.LicenseNormalQuestion;
import com.server.computer_science.question.normal_question.common.domain.NormalQuestion;
import com.server.computer_science.question.normal_question.common.service.NormalQuestionClassifyByCategoryService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BasicNormalQuestionClassifyByCategoryService implements NormalQuestionClassifyByCategoryService {
    /*
    Generic Type으로 변경 검토
     */

    @Override
    public Map<QuestionCategory, List<NormalQuestion>> classifyNormalQuestionByCategory(List<NormalQuestion> normalQuestions) {
        //        // 요청된 모든 카테고리에 대해 문제가 없어도 빈 리스트 보장
//        requestGetNormalQuestionsDto.getQuestionCategories().forEach(category ->
//                categoryMap.putIfAbsent(category, new ArrayList<>())
//
        return normalQuestions.stream().collect(Collectors.groupingBy(NormalQuestion::getQuestionCategory));
    }

    @Override
    public Map<QuestionCategory, List<LicenseNormalQuestion>> classifyLicenseNormalQuestionByCategoryOrdered(List<LicenseNormalQuestion> licenseNormalQuestions) {
        return licenseNormalQuestions.stream()
                .collect(Collectors.groupingBy(
                        LicenseNormalQuestion::getQuestionCategory,
                        LinkedHashMap::new, // 순서를 보장하는 LinkedHashMap 사용
                        Collectors.toList()
                ));
    }
}
