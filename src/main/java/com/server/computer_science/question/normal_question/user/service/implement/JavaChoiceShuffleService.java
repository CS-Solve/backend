package com.server.computer_science.question.normal_question.user.service.implement;


import com.server.computer_science.question.normal_question.common.domain.NormalQuestionChoice;
import com.server.computer_science.question.normal_question.user.service.ChoiceShuffleService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class JavaChoiceShuffleService implements ChoiceShuffleService {
    @Override
    public void shuffle(List<NormalQuestionChoice> normalQuestionChoices) {
        Collections.shuffle(normalQuestionChoices);
    }
}
