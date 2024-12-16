package com.comssa.api.question.service.common.implement;


import com.comssa.api.question.service.common.ChoiceShuffleService;
import com.comssa.persistence.question.major.domain.common.MajorQuestionChoice;
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
