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
public class ResponseNormalQuestionDtoForAdmin extends ResponseNormalQuestionDto{
    private boolean ifApproved;
    private boolean canBeShortAnswered;


    public ResponseNormalQuestionDtoForAdmin(String question, QuestionCategory questionCategory, QuestionLevel questionLevel, List<ResponseNormalQuestionChoiceDto> normalQuestionChoices, String description, boolean ifApproved, boolean canBeShortAnswered) {
        super(question, questionCategory, questionLevel, normalQuestionChoices, description);
        this.ifApproved = ifApproved;
        this.canBeShortAnswered = canBeShortAnswered;
    }
}
