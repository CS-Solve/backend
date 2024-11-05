package com.server.computer_science.question.major_question.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.server.computer_science.question.major_question.common.domain.MajorQuestionChoice;

@Service
public interface ChoiceShuffleService {

	public void shuffle(List<MajorQuestionChoice> majorQuestionChoices);

}
