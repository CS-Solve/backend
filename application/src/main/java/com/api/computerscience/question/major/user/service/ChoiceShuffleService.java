package com.api.computerscience.question.major.user.service;


import com.persistence.computerscience.question.major.domain.common.MajorQuestionChoice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChoiceShuffleService {

	void shuffle(List<MajorQuestionChoice> majorQuestionChoices);

}
