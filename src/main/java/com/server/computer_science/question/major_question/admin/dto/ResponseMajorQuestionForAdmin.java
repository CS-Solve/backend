package com.server.computer_science.question.major_question.admin.dto;


import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.common.domain.QuestionLevel;
import com.server.computer_science.question.common.dto.response.ResponseQuestionChoiceDto;
import com.server.computer_science.question.common.dto.response.ResponseQuestionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


import java.util.List;

@NoArgsConstructor
@Getter
@SuperBuilder
public class ResponseMajorQuestionForAdmin extends ResponseQuestionDto {
    private boolean ifApproved;
    private boolean canBeShortAnswered;

    public ResponseMajorQuestionForAdmin(Long id, String question, QuestionCategory questionCategory, QuestionLevel questionLevel, List<ResponseQuestionChoiceDto> normalQuestionChoices, String description, boolean ifApproved, boolean canBeShortAnswered) {
        super(id,question, questionCategory, questionLevel, normalQuestionChoices, description);
        this.ifApproved = ifApproved;
        this.canBeShortAnswered = canBeShortAnswered;
    }
}
