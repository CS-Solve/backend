package com.comssa.api.question.major.user.service;


import com.comssa.persistence.question.major.domain.common.MajorQuestionChoice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChoiceShuffleService {

	void shuffle(List<MajorQuestionChoice> majorQuestionChoices);

}
