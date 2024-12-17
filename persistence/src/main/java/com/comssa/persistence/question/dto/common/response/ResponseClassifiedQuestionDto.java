package com.comssa.persistence.question.dto.common.response;

import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@SuperBuilder
public class ResponseClassifiedQuestionDto {
    private QuestionCategory questionCategory;
    private List<ResponseQuestionDto> responseQuestionDtos;


    public static <T extends Question> ResponseClassifiedQuestionDto from(
            QuestionCategory questionCategory,
            List<T> question) {
        return ResponseClassifiedQuestionDto.builder()
                .questionCategory(questionCategory)
                .responseQuestionDtos(
                        question.stream()
                                .map(ResponseQuestionDto::from)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
