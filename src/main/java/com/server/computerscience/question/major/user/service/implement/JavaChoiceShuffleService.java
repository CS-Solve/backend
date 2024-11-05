package com.server.computerscience.question.major.user.service.implement;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.server.computerscience.question.major.common.domain.MajorQuestionChoice;
import com.server.computerscience.question.major.user.service.ChoiceShuffleService;

@Service
public class JavaChoiceShuffleService implements ChoiceShuffleService {
	@Override
	public void shuffle(List<MajorQuestionChoice> majorQuestionChoices) {
		Collections.shuffle(majorQuestionChoices);
	}
}
