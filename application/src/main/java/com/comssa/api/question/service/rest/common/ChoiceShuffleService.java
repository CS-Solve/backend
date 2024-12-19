package com.comssa.api.question.service.rest.common;


import com.comssa.persistence.question.domain.major.MajorQuestionChoice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChoiceShuffleService {

	void shuffle(List<MajorQuestionChoice> majorQuestionChoices);

}
