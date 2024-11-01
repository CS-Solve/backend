package com.server.computer_science.question.common.dto.response;


import com.server.computer_science.question.common.domain.Question;
import com.server.computer_science.question.common.domain.QuestionChoice;
import com.server.computer_science.question.license_question.domain.LicenseMultipleChoiceQuestion;
import com.server.computer_science.question.major_question.admin.dto.ResponseMajorQuestionForAdmin;
import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.common.domain.QuestionLevel;
import com.server.computer_science.question.major_question.common.domain.MajorMultipleChoiceQuestion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@SuperBuilder
public class ResponseQuestionDto {
    private Long id;
    private String content;
    private String description;
    private String imageUrl;
    private QuestionCategory questionCategory;
    private QuestionLevel questionLevel;
    private List<ResponseQuestionChoiceDto> questionChoices;
    /**
     * 유저와 관리자에 따라 다른 정적팩토리 메소드를 사용한다
     */
    public static <T extends Question> ResponseQuestionDto forUser(
            T question,
            List<? extends QuestionChoice> choiceExtractor){
        return ResponseQuestionDto.builder()
                .id(question.getId())
                .content(question.getContent())
                .questionChoices(
                        choiceExtractor
                                .stream()
                                .map(ResponseQuestionChoiceDto::of)
                                .collect(Collectors.toList())
                )
                .questionCategory(question.getQuestionCategory())
                .questionLevel(question.getQuestionLevel())
                .description(question.getDescription())
                .imageUrl(question.getImageUrl())
                .build();
    }
    /**
     * 차이점은 NormalQuesiton시 주관식 가능 여부와, 허용됐는지 여부 변수의 존재 여부다
     */
    public static ResponseQuestionDto forAdmin(MajorMultipleChoiceQuestion question){
        return ResponseMajorQuestionForAdmin.builder()
                .id(question.getId())
                .content(question.getContent())
                .questionChoices(
                        question.getQuestionChoices()
                                .stream()
                                .map(ResponseQuestionChoiceDto::of)
                                .collect(Collectors.toList())
                )
                .questionCategory(question.getQuestionCategory())
                .questionLevel(question.getQuestionLevel())
                .description(question.getDescription())
                .ifApproved(question.isIfApproved())
                .canBeShortAnswered(question.isCanBeShortAnswered())
                .build();
    }
    public static ResponseQuestionDto forAdmin(LicenseMultipleChoiceQuestion question){
        return ResponseMajorQuestionForAdmin.builder()
                .id(question.getId())
                .content(question.getContent())
                .questionChoices(
                        question.getNormalQuestionChoices()
                                .stream()
                                .map(ResponseQuestionChoiceDto::of)
                                .collect(Collectors.toList())
                )
                .questionCategory(question.getQuestionCategory())
                .questionLevel(question.getQuestionLevel())
                .imageUrl(question.getImageUrl())
                .description(question.getDescription())
                .build();
    }

    public ResponseQuestionDto(Long id, String content, QuestionCategory questionCategory, QuestionLevel questionLevel, List<ResponseQuestionChoiceDto> questionChoices, String description) {
        this.id = id;
        this.content = content;
        this.questionCategory = questionCategory;
        this.questionLevel = questionLevel;
        this.questionChoices = questionChoices;
        this.description = description;
    }
}
