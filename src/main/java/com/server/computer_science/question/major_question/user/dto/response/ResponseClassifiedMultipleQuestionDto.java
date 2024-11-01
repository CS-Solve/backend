package com.server.computer_science.question.major_question.user.dto.response;

import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.common.dto.response.ResponseQuestionDto;
import com.server.computer_science.question.license_question.domain.LicenseMultipleChoiceQuestion;
import com.server.computer_science.question.major_question.common.domain.MajorMultipleChoiceQuestion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@SuperBuilder
public class ResponseClassifiedMultipleQuestionDto {
    private QuestionCategory questionCategory;
    private List<ResponseQuestionDto> responseQuestionDtoList;

    /**
     * 일반 CS 문제 전용
     */
    public static ResponseClassifiedMultipleQuestionDto normalQuestionForUser(QuestionCategory questionCategory, List<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestions) {
        return ResponseClassifiedMultipleQuestionDto.builder()
                .questionCategory(questionCategory)
                .responseQuestionDtoList(
                        majorMultipleChoiceQuestions.stream()
                                .map(question -> ResponseQuestionDto.forUser(
                                        question,question.getQuestionChoices()
                                ))
                                .collect(Collectors.toList())
                )
                .build();
    }


    public static ResponseClassifiedMultipleQuestionDto LicenseQuestionForUser(QuestionCategory questionCategory, List<LicenseMultipleChoiceQuestion> normalQuestions ) {
        return ResponseClassifiedMultipleQuestionDto.builder()
                .questionCategory(questionCategory)
                .responseQuestionDtoList(
                        normalQuestions.stream()
                                .map(question -> ResponseQuestionDto.forUser(
                                        question,question.getNormalQuestionChoices()
                                ))
                                .collect(Collectors.toList())
                )
                .build();
    }
    public static ResponseClassifiedMultipleQuestionDto forAdmin(QuestionCategory questionCategory, List<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestions) {
        return ResponseClassifiedMultipleQuestionDto.builder()
                .questionCategory(questionCategory)
                .responseQuestionDtoList(
                        majorMultipleChoiceQuestions.stream()
                                .map(ResponseQuestionDto::forAdmin)
                                .collect(Collectors.toList())
                )
                .build();
    }


    public ResponseClassifiedMultipleQuestionDto(QuestionCategory questionCategory, List<ResponseQuestionDto> responseQuestionDtoList) {
        this.questionCategory = questionCategory;
        this.responseQuestionDtoList = responseQuestionDtoList;
    }
}
