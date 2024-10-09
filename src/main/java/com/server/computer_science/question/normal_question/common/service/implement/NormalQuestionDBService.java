package com.server.computer_science.question.normal_question.common.service.implement;

import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.common.QuestionLevel;
import com.server.computer_science.question.normal_question.common.domain.NormalQuestion;
import com.server.computer_science.question.normal_question.common.repository.NormalQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class NormalQuestionDBService {
    private final NormalQuestionRepository normalQuestionRepository;

    public List<NormalQuestion> getFetchChoicesByCategoriesAndLevels(List<QuestionCategory> categories, List<QuestionLevel> questionLevels){
        return normalQuestionRepository.findNormalQuestionsFetchChoicesWithCategoriesAndLevels(categories,questionLevels);
    }

    public List<NormalQuestion> findAllFetchChoices(){
        return normalQuestionRepository.findNormalQuestionsFetchChoices();
    }

    public NormalQuestion findById(Long id){
        return normalQuestionRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

}
