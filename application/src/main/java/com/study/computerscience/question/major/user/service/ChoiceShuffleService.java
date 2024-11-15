package com.study.computerscience.question.major.user.service;

import com.study.computerscience.question.major.common.domain.MajorQuestionChoice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChoiceShuffleService {

	void shuffle(List<MajorQuestionChoice> majorQuestionChoices);

}
