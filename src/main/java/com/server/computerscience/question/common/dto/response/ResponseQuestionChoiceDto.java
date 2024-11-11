package com.server.computerscience.question.common.dto.response;

import com.server.computerscience.question.common.domain.QuestionChoice;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Getter
public class ResponseQuestionChoiceDto {

	private Long id;
	private String content;
	private int selectedCount;
	private boolean answerStatus;

	@Builder
	public ResponseQuestionChoiceDto(Long id, String content, int selectedCount, boolean answerStatus) {
		this.id = id;
		this.content = content;
		this.selectedCount = selectedCount;
		this.answerStatus = answerStatus;
	}

	public static <T extends QuestionChoice> ResponseQuestionChoiceDto of(T questionChoice) {
		return ResponseQuestionChoiceDto.builder()
			.content(questionChoice.getContent())
			.selectedCount(questionChoice.getSelectedCount())
			.answerStatus(questionChoice.isAnswerStatus())
			.build();
	}
}
