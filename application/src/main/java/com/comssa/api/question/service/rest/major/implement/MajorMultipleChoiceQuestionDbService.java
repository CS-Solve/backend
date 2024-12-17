package com.comssa.api.question.service.rest.major.implement;


import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.common.QuestionLevel;
import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion;
import com.comssa.persistence.question.repository.MajorMultipleChoiceQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class MajorMultipleChoiceQuestionDbService {
    private final MajorMultipleChoiceQuestionRepository majorMultipleChoiceQuestionRepository;

    /**
     * 허용되지 않은 모든 문제까지 조회 (주로 관리자)
     */
    // 기본 개별 조회
    public MajorMultipleChoiceQuestion findById(Long id) {
        return majorMultipleChoiceQuestionRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    // 카테고리, 레벨로 조회 - 선택지까지
    public List<MajorMultipleChoiceQuestion> getFetchChoicesByCategoriesAndLevels(
            List<QuestionCategory> categories,
            List<QuestionLevel> questionLevels) {
        return majorMultipleChoiceQuestionRepository.findFetchChoicesWithCategoriesAndLevels(categories,
                questionLevels);
    }

    // 전체 조회 - 선택지까지, 주관식 가능한 것들만
    public List<MajorMultipleChoiceQuestion> findAllFetchChoicesShortAnswered() {
        return majorMultipleChoiceQuestionRepository.findFetchChoicesShortAnswered();
    }

    // 전체 조회 -선택지까지
    public List<MajorMultipleChoiceQuestion> findAllFetchChoices() {
        return majorMultipleChoiceQuestionRepository.findFetchChoices();
    }

    //전체 조회 - 선택지까지, 정렬
    public List<MajorMultipleChoiceQuestion> findAllFetchChoicesSortedByApproveAndShortAnswered() {
        return majorMultipleChoiceQuestionRepository.findFetchChoicesSortedByIfApprovedAndCanBeShortAnswered();
    }

    // 개별 조회 - 선택지까지
    public MajorMultipleChoiceQuestion findByIdFetchChoices(Long id) {
        return majorMultipleChoiceQuestionRepository.findByIdFetchChoices(id).orElseThrow(NoSuchElementException::new);
    }

    // id로 삭제
    public void deleteById(Long id) {
        majorMultipleChoiceQuestionRepository.deleteById(id);
    }

    // 개별 삭제
    public void deleteNormalQuestion(MajorMultipleChoiceQuestion majorMultipleChoiceQuestion) {
        majorMultipleChoiceQuestionRepository.delete(majorMultipleChoiceQuestion);
    }

    /**
     * 허용된 문제만을 조회 (주로 유저)
     */
    // 카테고리, 레벨로 조회 - 선택지까지
    public List<MajorMultipleChoiceQuestion> findAllFetchChoicesByCategoriesAndLevelsApproved(
            List<QuestionCategory> categories, List<QuestionLevel> questionLevels) {
        return majorMultipleChoiceQuestionRepository.findFetchChoicesWithCategoriesAndLevelsAndIfApproved(categories,
                questionLevels);
    }

    // 카테고리, 레벨로 조회 - 선택지까지, 주관식만
    public List<MajorMultipleChoiceQuestion> findAllFetchChoicesByCategoriesAndLevelsApprovedAndShortAnswered(
            List<QuestionCategory> categories, List<QuestionLevel> questionLevels) {
        return majorMultipleChoiceQuestionRepository
                .findFetchChoicesWithCategoriesAndLevelsAndIfApprovedAndCanBeShortAnswered(
                        categories, questionLevels);
    }

}
