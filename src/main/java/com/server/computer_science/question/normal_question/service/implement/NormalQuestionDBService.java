package com.server.computer_science.question.normal_question.service.implement;

import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.common.QuestionLevel;
import com.server.computer_science.question.normal_question.domain.NormalQuestion;
import com.server.computer_science.question.normal_question.repository.NormalQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NormalQuestionDBService {
    private final NormalQuestionRepository normalQuestionRepository;

    public List<NormalQuestion> getNormalQuestionsByCategoriesAndLevels(List<QuestionCategory> categories, List<QuestionLevel> questionLevels){
        return normalQuestionRepository.findNormalQuestionsFetchChoicesWithCategoriesAndLevels(categories,questionLevels);
    }

    public List<NormalQuestion> findAllFetchChoices(){
        return normalQuestionRepository.findNormalQuestionsFetchChoices();
    }

}
