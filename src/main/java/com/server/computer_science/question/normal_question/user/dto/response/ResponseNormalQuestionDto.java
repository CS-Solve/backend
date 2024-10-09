package com.server.computer_science.question.normal_question.user.dto.response;


import com.server.computer_science.question.normal_question.admin.dto.ResponseNormalQuestionDtoForAdmin;
import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.common.QuestionLevel;
import com.server.computer_science.question.normal_question.common.domain.NormalQuestion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@SuperBuilder
public class ResponseNormalQuestionDto {
    private Long id;
    private String question;
    private String description;
    private QuestionCategory questionCategory;
    private QuestionLevel questionLevel;
    private List<ResponseNormalQuestionChoiceDto> normalQuestionChoices;


    /**
     * 유저와 관리자에 따라 다른 정적팩토리 메소드를 사용한다
     * 차이점은 객관식 여부와, 허용됐는지 여부 변수의 존재 여부다
     */
    public static ResponseNormalQuestionDto forUser(NormalQuestion question){
        return ResponseNormalQuestionDto.builder()
                .id(question.getId())
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
                .id(question.getId())
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

    public ResponseNormalQuestionDto(Long id,String question, QuestionCategory questionCategory, QuestionLevel questionLevel, List<ResponseNormalQuestionChoiceDto> normalQuestionChoices, String description) {
        this.id = id;
        this.question = question;
        this.questionCategory = questionCategory;
        this.questionLevel = questionLevel;
        this.normalQuestionChoices = normalQuestionChoices;
        this.description = description;
    }
}
