package com.study.computerscience.question.major.user.service.implement;

import com.study.computerscience.question.major.common.domain.MajorQuestionChoice;
import com.study.computerscience.question.major.user.service.ChoiceShuffleService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class JavaChoiceShuffleService implements ChoiceShuffleService {
	@Override
	public void shuffle(List<MajorQuestionChoice> majorQuestionChoices) {
		Collections.shuffle(majorQuestionChoices);
	}
}
