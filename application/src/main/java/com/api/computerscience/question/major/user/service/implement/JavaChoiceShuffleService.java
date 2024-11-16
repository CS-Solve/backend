package com.api.computerscience.question.major.user.service.implement;


import com.api.computerscience.question.major.user.service.ChoiceShuffleService;
import com.persistence.computerscience.question.major.domain.common.MajorQuestionChoice;
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
