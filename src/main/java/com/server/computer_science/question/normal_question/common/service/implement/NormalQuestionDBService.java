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

    /**
     * 허용되지 않은 모든 문제까지 조회 (주로 관리자)
     */
    // 카테고리, 레벨로 조회 - 선택지까지
    public List<NormalQuestion> getFetchChoicesByCategoriesAndLevels(List<QuestionCategory> categories, List<QuestionLevel> questionLevels){
        return normalQuestionRepository.findNormalQuestionsFetchChoicesWithCategoriesAndLevels(categories,questionLevels);
    }
    // 전체 조회 -선택지까지
    public List<NormalQuestion> findAllFetchChoices(){
        return normalQuestionRepository.findNormalQuestionsFetchChoices();
    }
    //전체 조회 - 선택지까지, 관리자용
    public List<NormalQuestion> findAllFetchChoicesSortedByApproveAndShortAnswered(){
        return normalQuestionRepository.findNormalQuestionsFetchChoicesSortedByIfApprovedAndCanBeShortAnswered();
    }
    // 개별 조회 - 선택지까지
    public NormalQuestion findByIdFetchChoices(Long id){
        return normalQuestionRepository.findNormalQuestionsByIdFetchChoices(id).orElseThrow(NoSuchElementException::new);
    }
    // 개별 삭제
    public void deleteNormalQuestion(NormalQuestion normalQuestion){
        normalQuestionRepository.delete(normalQuestion);
    }


    /**
     * 허용된 문제만을 조회 (주로 유저)
     */
    // 카테고리, 레벨로 조회 - 선택지까지
    public List<NormalQuestion> getAllFetchChoicesByCategoriesAndLevelsApproved(List<QuestionCategory> categories, List<QuestionLevel> questionLevels){
        return normalQuestionRepository.findNormalQuestionsFetchChoicesWithCategoriesAndLevelsAndIfApproved(categories,questionLevels);
    }







}
