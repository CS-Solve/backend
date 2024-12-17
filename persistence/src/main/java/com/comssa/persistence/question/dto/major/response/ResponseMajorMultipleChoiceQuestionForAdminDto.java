package com.comssa.persistence.question.dto.major.response;

import com.comssa.persistence.question.dto.common.response.ResponseMultipleChoiceQuestionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ResponseMajorMultipleChoiceQuestionForAdminDto extends ResponseMultipleChoiceQuestionDto {
    private boolean canBeShortAnswered;
}
