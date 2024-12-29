package com.comssa.persistence.question.domain.major;

import com.comssa.persistence.question.domain.common.ChoiceBehavior;
import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.common.QuestionLevel;
import com.comssa.persistence.question.dto.common.request.RequestMakeMultipleChoiceQuestionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@ToString
@DiscriminatorValue("MM")
public class MajorMultipleChoiceQuestion extends Question implements ChoiceBehavior<MajorQuestionChoice> {
	public boolean canBeShortAnswered;

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MajorQuestionChoice> questionChoices;

	public static MajorMultipleChoiceQuestion makeWithDto(RequestMakeMultipleChoiceQuestionDto dto) {
		MajorMultipleChoiceQuestion majorMultipleChoiceQuestion = MajorMultipleChoiceQuestion.builder()
			.content(dto.getContent())
			.questionCategory(dto.getQuestionCategory())
			.questionLevel(dto.getQuestionLevel())
			.description(dto.getDescription())
			.imageUrl(null)
			.build();
		majorMultipleChoiceQuestion.initDefaults();
		return majorMultipleChoiceQuestion;
	}

	public static MajorMultipleChoiceQuestion makeForTest() {
		return MajorMultipleChoiceQuestion.builder()
			.id(1L)
			.content("testQuest")
			.questionCategory(QuestionCategory.COMPUTER_ARCHITECTURE)
			.questionLevel(QuestionLevel.LOW)
			.questionChoices(new ArrayList<>())
			.description("testDescription")
			.build();
	}

	public void initDefaults() {
		this.questionChoices = new ArrayList<>();
		this.canBeShortAnswered = false;
		this.ifApproved = false;
	}

	public void toggleCanBeShortAnswered() {
		this.canBeShortAnswered = !this.canBeShortAnswered;
	}

	public boolean isFit(QuestionCategory questionCategory, QuestionLevel questionLevel) {
		return this.questionCategory.equals(questionCategory) && this.questionLevel.equals(questionLevel);
	}
}
