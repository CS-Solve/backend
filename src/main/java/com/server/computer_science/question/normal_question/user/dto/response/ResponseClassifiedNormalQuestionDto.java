package com.server.computer_science.question.normal_question.user.dto.response;

import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.common.dto.response.ResponseNormalQuestionDto;
import com.server.computer_science.question.license_question.domain.LicenseNormalQuestion;
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

    /**
     * 일반 CS 문제 전용
     */
    public static ResponseClassifiedNormalQuestionDto normalQuestionForUser(QuestionCategory questionCategory, List<NormalQuestion> normalQuestions ) {
        return ResponseClassifiedNormalQuestionDto.builder()
                .questionCategory(questionCategory)
                .responseNormalQuestionDtoList(
                        normalQuestions.stream()
                                .map(ResponseNormalQuestionDto::forUser)
                                .collect(Collectors.toList())
                )
                .build();
    }


    public static ResponseClassifiedNormalQuestionDto LicenseQuestionForUser(QuestionCategory questionCategory, List<LicenseNormalQuestion> normalQuestions ) {
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
