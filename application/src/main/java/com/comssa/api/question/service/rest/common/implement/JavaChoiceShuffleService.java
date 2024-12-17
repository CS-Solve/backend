package com.comssa.api.question.service.rest.common.implement;


import com.comssa.api.question.service.rest.common.ChoiceShuffleService;
import com.comssa.persistence.question.domain.major.MajorQuestionChoice;
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
