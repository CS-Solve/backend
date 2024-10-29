package com.server.computer_science.question.normal_question.user.service;


import com.server.computer_science.question.normal_question.common.domain.QuestionChoice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChoiceShuffleService {

    public void shuffle(List<QuestionChoice> questionChoices);

}
