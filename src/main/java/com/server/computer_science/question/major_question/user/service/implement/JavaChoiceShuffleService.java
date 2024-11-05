package com.server.computer_science.question.major_question.user.service.implement;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.server.computer_science.question.major_question.common.domain.MajorQuestionChoice;
import com.server.computer_science.question.major_question.user.service.ChoiceShuffleService;

@Service
public class JavaChoiceShuffleService implements ChoiceShuffleService {
	@Override
	public void shuffle(List<MajorQuestionChoice> majorQuestionChoices) {
		Collections.shuffle(majorQuestionChoices);
	}
}
