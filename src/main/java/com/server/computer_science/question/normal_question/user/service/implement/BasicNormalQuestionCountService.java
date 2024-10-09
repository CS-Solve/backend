package com.server.computer_science.question.normal_question.user.service.implement;

import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.common.QuestionLevel;
import com.server.computer_science.question.normal_question.common.domain.NormalQuestion;
import com.server.computer_science.question.normal_question.common.service.implement.NormalQuestionDBService;
import com.server.computer_science.question.normal_question.user.dto.response.ResponseNormalQuestionClassCountDto;
import com.server.computer_science.question.normal_question.user.service.NormalQuestionCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasicNormalQuestionCountService implements NormalQuestionCountService {
    private final NormalQuestionDBService normalQuestionDBService;
    /**
     *
     * Unique한 분야-난이도 별로 분류하고, 분류별 문제의 수를 판별한다.
     * 분야-난이도별 분류는 Map을 사용한다.
     */
    @Override
    public List<ResponseNormalQuestionClassCountDto> getNormalQuestionCountByClass() {
        List<NormalQuestion> normalQuestions = normalQuestionDBService.findAllFetchChoices();
        Map<Pair<QuestionCategory, QuestionLevel>,Integer> counts = initateCountMap();
        for (NormalQuestion normalQuestion : normalQuestions) {
            for(Pair<QuestionCategory,QuestionLevel> pair : counts.keySet()){
                if(normalQuestion.isFit(pair.getFirst(),pair.getSecond())){
                    counts.put(pair,counts.get(pair)+1);
                }
            }
        }
        return counts.entrySet().stream()
                .map(pairIntegerEntry -> {
                    QuestionCategory questionCategory = pairIntegerEntry.getKey().getFirst();
                    QuestionLevel questionLevel = pairIntegerEntry.getKey().getSecond();
                    int count = pairIntegerEntry.getValue();
                    return ResponseNormalQuestionClassCountDto.of(questionCategory,questionLevel,count);
                })
                .collect(Collectors.toList());
    }

    private Map<Pair<QuestionCategory,QuestionLevel>,Integer> initateCountMap() {
        Map<Pair<QuestionCategory,QuestionLevel>,Integer> counts = new LinkedHashMap<>();
        for(QuestionCategory questionCategory : QuestionCategory.values()){
            for(QuestionLevel questionLevel : QuestionLevel.values()){
                counts.put(Pair.of(questionCategory,questionLevel),0);
            }
        }
        return counts;
    }
}
