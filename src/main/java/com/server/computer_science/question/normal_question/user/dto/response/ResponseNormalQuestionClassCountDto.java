package com.server.computer_science.question.normal_question.user.dto.response;


import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.common.domain.QuestionLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ResponseNormalQuestionClassCountDto {
    private QuestionCategory questionCategory;
    private QuestionLevel questionLevel;
    private int count;
    public static ResponseNormalQuestionClassCountDto of(QuestionCategory questionCategory, QuestionLevel questionLevel, int count){
        return ResponseNormalQuestionClassCountDto.builder()
                .questionLevel(questionLevel)
                .questionCategory(questionCategory)
                .count(count)
                .build();
    }

    @Builder
    public ResponseNormalQuestionClassCountDto(QuestionCategory questionCategory, QuestionLevel questionLevel, int count) {
        this.questionCategory = questionCategory;
        this.questionLevel = questionLevel;
        this.count = count;
    }
}
