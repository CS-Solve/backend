package com.server.computer_science.question.normal_question.dto.response;

import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.normal_question.domain.NormalQuestion;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class ResponseClassifiedNormalQuestionDto {
    private QuestionCategory questionCategory;
    private List<ResponseNormalQuestionDto> responseNormalQuestionDtoList;

    public static ResponseClassifiedNormalQuestionDto of(QuestionCategory questionCategory, List<NormalQuestion> normalQuestions ) {
        return ResponseClassifiedNormalQuestionDto.builder()
                .questionCategory(questionCategory)
                .responseNormalQuestionDtoList(
                        normalQuestions.stream()
                                .map(ResponseNormalQuestionDto::of)
                                .collect(Collectors.toList())
                )
                .build();
    }
    @Builder
    public ResponseClassifiedNormalQuestionDto(QuestionCategory questionCategory, List<ResponseNormalQuestionDto> responseNormalQuestionDtoList) {
        this.questionCategory = questionCategory;
        this.responseNormalQuestionDtoList = responseNormalQuestionDtoList;
    }
}
