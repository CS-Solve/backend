package com.server.computerscience.question.major.common.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.server.computerscience.question.common.domain.ChoiceProvider;
import com.server.computerscience.question.common.domain.Question;
import com.server.computerscience.question.common.domain.QuestionCategory;
import com.server.computerscience.question.common.domain.QuestionLevel;
import com.server.computerscience.question.major.admin.dto.RequestMakeMultipleChoiceQuestionDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@ToString
public class MajorMultipleChoiceQuestion extends Question implements ChoiceProvider {
	public boolean canBeShortAnswered;
	public boolean ifApproved;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
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

	public void toggleApproved() {
		this.ifApproved = !this.ifApproved;
	}

	public void toggleCanBeShortAnswered() {
		this.canBeShortAnswered = !this.canBeShortAnswered;
	}

	public boolean isFit(QuestionCategory questionCategory, QuestionLevel questionLevel) {
		return this.questionCategory.equals(questionCategory) && this.questionLevel.equals(questionLevel);
	}

	/**
	 * Dto 반환시 Generic을 쓰기위해 상위 추상 클래스에 포함한 메소드
	 */
	@Override
	public Long getId() {
		return id;
	}
}
