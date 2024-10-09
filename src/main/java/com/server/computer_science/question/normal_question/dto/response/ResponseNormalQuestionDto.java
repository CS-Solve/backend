package com.server.computer_science.question.normal_question.dto.response;


import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.common.QuestionLevel;
import com.server.computer_science.question.normal_question.domain.NormalQuestion;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@SuperBuilder
public class ResponseNormalQuestionDto {
    private String question;
    private String description;
    private QuestionCategory questionCategory;
    private QuestionLevel questionLevel;
    private List<ResponseNormalQuestionChoiceDto> normalQuestionChoices;


    public static ResponseNormalQuestionDto forUser(NormalQuestion question){
        return ResponseNormalQuestionDto.builder()
                .question(question.getQuestion())
                .normalQuestionChoices(
                        question.getNormalQuestionChoices()
                                .stream()
                                .map(ResponseNormalQuestionChoiceDto::of)
                                .collect(Collectors.toList())
                )
                .questionCategory(question.getQuestionCategory())
                .questionLevel(question.getQuestionLevel())
                .description(question.getDescription())
                .build();
    }
    public static ResponseNormalQuestionDto forAdmin(NormalQuestion question){
        return ResponseNormalQuestionDtoForAdmin.builder()
                .question(question.getQuestion())
                .normalQuestionChoices(
                        question.getNormalQuestionChoices()
                                .stream()
                                .map(ResponseNormalQuestionChoiceDto::of)
                                .collect(Collectors.toList())
                )
                .questionCategory(question.getQuestionCategory())
                .questionLevel(question.getQuestionLevel())
                .description(question.getDescription())
                .ifApproved(question.isIfApproved())
                .canBeShortAnswered(question.isCanBeShortAnswered())
                .build();
    }

    public ResponseNormalQuestionDto(String question, QuestionCategory questionCategory, QuestionLevel questionLevel, List<ResponseNormalQuestionChoiceDto> normalQuestionChoices, String description) {
        this.question = question;
        this.questionCategory = questionCategory;
        this.questionLevel = questionLevel;
        this.normalQuestionChoices = normalQuestionChoices;
        this.description = description;
    }
}
