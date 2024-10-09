package com.server.computer_science.question.admin.dto;


import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.common.QuestionLevel;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionChoiceDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


import java.util.List;

@NoArgsConstructor
@Getter
@SuperBuilder
public class ResponseNormalQuestionDtoForAdmin extends ResponseNormalQuestionDto {
    private boolean ifApproved;
    private boolean canBeShortAnswered;

    public ResponseNormalQuestionDtoForAdmin(String question, QuestionCategory questionCategory, QuestionLevel questionLevel, List<ResponseNormalQuestionChoiceDto> normalQuestionChoices, String description, boolean ifApproved, boolean canBeShortAnswered) {
        super(question, questionCategory, questionLevel, normalQuestionChoices, description);
        this.ifApproved = ifApproved;
        this.canBeShortAnswered = canBeShortAnswered;
    }
}
