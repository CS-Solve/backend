package com.comssa.api.question.service.rest.major.implement;


import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.common.QuestionLevel;
import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion;
import com.comssa.persistence.question.repository.MajorMultipleChoiceQuestionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class MajorMultipleChoiceQuestionDbService {
	private final MajorMultipleChoiceQuestionJpaRepository majorMultipleChoiceQuestionJpaRepository;

	/**
	 * 허용되지 않은 모든 문제까지 조회 (주로 관리자)
	 */
	// 기본 개별 조회
	public MajorMultipleChoiceQuestion findById(Long id) {
		return majorMultipleChoiceQuestionJpaRepository.findById(id).orElseThrow(NoSuchElementException::new);
	}

	//전체 조회 - 선택지까지, 정렬
	public List<MajorMultipleChoiceQuestion> findAllFetchChoicesSortedByApproveAndShortAnswered() {
		return majorMultipleChoiceQuestionJpaRepository.findFetchChoicesSortedByIfApprovedAndCanBeShortAnswered();
	}

	/**
	 * 허용된 문제만을 조회 (주로 유저)
	 */
	// 카테고리, 레벨로 조회 - 선택지까지
	public List<MajorMultipleChoiceQuestion> findAllFetchChoicesByCategoriesAndLevelsApproved(
		List<QuestionCategory> categories, List<QuestionLevel> questionLevels) {
		return majorMultipleChoiceQuestionJpaRepository.findFetchChoicesWithCategoriesAndLevelsAndIfApproved(categories,
			questionLevels);
	}


}
