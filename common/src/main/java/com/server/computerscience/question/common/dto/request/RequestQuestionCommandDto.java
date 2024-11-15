package com.server.computerscience.question.common.dto.request;

import com.server.computerscience.question.common.domain.QuestionCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestQuestionCommandDto {
	private String command;
	private boolean multipleChoice;
	private List<QuestionCategory> questionCategories;
}
