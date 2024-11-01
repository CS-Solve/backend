package com.server.computer_science.question.major_question.user.service;


import com.server.computer_science.question.major_question.common.domain.MajorQuestionChoice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChoiceShuffleService {

    public void shuffle(List<MajorQuestionChoice> majorQuestionChoices);

}
