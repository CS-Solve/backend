package com.server.computer_science.question.normal_question.user.dto.response;

import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.normal_question.common.domain.NormalQuestion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@SuperBuilder
public class ResponseClassifiedNormalQuestionDto {
    private QuestionCategory questionCategory;
    private List<ResponseNormalQuestionDto> responseNormalQuestionDtoList;

    public static ResponseClassifiedNormalQuestionDto forUser(QuestionCategory questionCategory, List<NormalQuestion> normalQuestions ) {
        return ResponseClassifiedNormalQuestionDto.builder()
                .questionCategory(questionCategory)
                .responseNormalQuestionDtoList(
                        normalQuestions.stream()
                                .map(ResponseNormalQuestionDto::forUser)
                                .collect(Collectors.toList())
                )
                .build();
    }
    public static ResponseClassifiedNormalQuestionDto forAdmin(QuestionCategory questionCategory, List<NormalQuestion> normalQuestions ) {
        return ResponseClassifiedNormalQuestionDto.builder()
                .questionCategory(questionCategory)
                .responseNormalQuestionDtoList(
                        normalQuestions.stream()
                                .map(ResponseNormalQuestionDto::forAdmin)
                                .collect(Collectors.toList())
                )
                .build();
    }
    public ResponseClassifiedNormalQuestionDto(QuestionCategory questionCategory, List<ResponseNormalQuestionDto> responseNormalQuestionDtoList) {
        this.questionCategory = questionCategory;
        this.responseNormalQuestionDtoList = responseNormalQuestionDtoList;
    }
}
