package com.server.computerscience.question.major.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.server.computerscience.question.major.common.domain.MajorQuestionChoice;

@Service
public interface ChoiceShuffleService {

	public void shuffle(List<MajorQuestionChoice> majorQuestionChoices);

}
