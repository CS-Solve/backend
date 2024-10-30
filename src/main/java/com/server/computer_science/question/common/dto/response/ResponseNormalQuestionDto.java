package com.server.computer_science.question.common.dto.response;


import com.server.computer_science.question.license_question.domain.LicenseNormalQuestion;
import com.server.computer_science.question.normal_question.admin.dto.ResponseNormalQuestionDtoForAdmin;
import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.common.domain.QuestionLevel;
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
    private String content;
    private String description;
    private String imageUrl;
    private QuestionCategory questionCategory;
    private QuestionLevel questionLevel;
    private List<ResponseNormalQuestionChoiceDto> questionChoices;


    /**
     * 유저와 관리자에 따라 다른 정적팩토리 메소드를 사용한다
     */
    public static ResponseNormalQuestionDto forUser(NormalQuestion question){
        return ResponseNormalQuestionDto.builder()
                .id(question.getId())
                .content(question.getContent())
                .questionChoices(
                        question.getQuestionChoices()
                                .stream()
                                .map(ResponseNormalQuestionChoiceDto::of)
                                .collect(Collectors.toList())
                )
                .questionCategory(question.getQuestionCategory())
                .questionLevel(question.getQuestionLevel())
                .description(question.getDescription())
                .build();
    }
    public static ResponseNormalQuestionDto forUser(LicenseNormalQuestion question){
        return ResponseNormalQuestionDto.builder()
                .id(question.getId())
                .content(question.getContent())
                .questionChoices(
                        question.getNormalQuestionChoices()
                                .stream()
                                .map(ResponseNormalQuestionChoiceDto::of)
                                .collect(Collectors.toList())
                )
                .questionCategory(question.getQuestionCategory())
                .questionLevel(question.getQuestionLevel())
                .imageUrl(question.getImageUrl())
                .description(question.getDescription())
                .build();
    }

    /**
     * 차이점은 NormalQuesiton시 주관식 가능 여부와, 허용됐는지 여부 변수의 존재 여부다
     */
    public static ResponseNormalQuestionDto forAdmin(NormalQuestion question){
        return ResponseNormalQuestionDtoForAdmin.builder()
                .id(question.getId())
                .content(question.getContent())
                .questionChoices(
                        question.getQuestionChoices()
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
    public static ResponseNormalQuestionDto forAdmin(LicenseNormalQuestion question){
        return ResponseNormalQuestionDtoForAdmin.builder()
                .id(question.getId())
                .content(question.getContent())
                .questionChoices(
                        question.getNormalQuestionChoices()
                                .stream()
                                .map(ResponseNormalQuestionChoiceDto::of)
                                .collect(Collectors.toList())
                )
                .questionCategory(question.getQuestionCategory())
                .questionLevel(question.getQuestionLevel())
                .imageUrl(question.getImageUrl())
                .description(question.getDescription())
                .build();
    }

    public ResponseNormalQuestionDto(Long id, String content, QuestionCategory questionCategory, QuestionLevel questionLevel, List<ResponseNormalQuestionChoiceDto> questionChoices, String description) {
        this.id = id;
        this.content = content;
        this.questionCategory = questionCategory;
        this.questionLevel = questionLevel;
        this.questionChoices = questionChoices;
        this.description = description;
    }
}
