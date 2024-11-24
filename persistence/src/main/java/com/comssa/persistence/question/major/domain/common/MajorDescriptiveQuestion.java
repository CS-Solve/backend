package com.comssa.persistence.question.major.domain.common;

import com.comssa.persistence.question.common.domain.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@ToString
@DiscriminatorValue("MD")
public class MajorDescriptiveQuestion extends Question {
	private String gradeStandard;

	@Override
	public void initDefaults() {

	}
}
