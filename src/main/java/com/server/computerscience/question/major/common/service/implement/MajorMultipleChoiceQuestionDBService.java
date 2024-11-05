package com.server.computerscience.question.major.common.service.implement;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.computerscience.question.common.domain.QuestionCategory;
import com.server.computerscience.question.common.domain.QuestionLevel;
import com.server.computerscience.question.major.common.domain.MajorMultipleChoiceQuestion;
import com.server.computerscience.question.major.common.repository.MajorQuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MajorMultipleChoiceQuestionDBService {
	private final MajorQuestionRepository majorQuestionRepository;

	/**
	 * 허용되지 않은 모든 문제까지 조회 (주로 관리자)
	 */
	// 기본 개별 조회
	public MajorMultipleChoiceQuestion findById(Long id) {
		return majorQuestionRepository.findById(id).orElseThrow(NoSuchElementException::new);
	}

	// 카테고리, 레벨로 조회 - 선택지까지
	public List<MajorMultipleChoiceQuestion> getFetchChoicesByCategoriesAndLevels(List<QuestionCategory> categories,
		List<QuestionLevel> questionLevels) {
		return majorQuestionRepository.findFetchChoicesWithCategoriesAndLevels(categories, questionLevels);
	}

	// 전체 조회 - 선택지까지, 주관식 가능한 것들만
	public List<MajorMultipleChoiceQuestion> findAllFetchChoicesShortAnswered() {
		return majorQuestionRepository.findFetchChoicesShortAnswered();
	}

	// 전체 조회 -선택지까지
	public List<MajorMultipleChoiceQuestion> findAllFetchChoices() {
		return majorQuestionRepository.findFetchChoices();
	}

	//전체 조회 - 선택지까지, 정렬
	public List<MajorMultipleChoiceQuestion> findAllFetchChoicesSortedByApproveAndShortAnswered() {
		return majorQuestionRepository.findFetchChoicesSortedByIfApprovedAndCanBeShortAnswered();
	}

	// 개별 조회 - 선택지까지
	public MajorMultipleChoiceQuestion findByIdFetchChoices(Long id) {
		return majorQuestionRepository.findByIdFetchChoices(id).orElseThrow(NoSuchElementException::new);
	}

	// id로 삭제
	public void deleteById(Long id) {
		majorQuestionRepository.deleteById(id);
	}

	// 개별 삭제
	public void deleteNormalQuestion(MajorMultipleChoiceQuestion majorMultipleChoiceQuestion) {
		majorQuestionRepository.delete(majorMultipleChoiceQuestion);
	}

	/**
	 * 허용된 문제만을 조회 (주로 유저)
	 */
	// 카테고리, 레벨로 조회 - 선택지까지
	public List<MajorMultipleChoiceQuestion> findAllFetchChoicesByCategoriesAndLevelsApproved(
		List<QuestionCategory> categories, List<QuestionLevel> questionLevels) {
		return majorQuestionRepository.findFetchChoicesWithCategoriesAndLevelsAndIfApproved(categories, questionLevels);
	}

	// 카테고리, 레벨로 조회 - 선택지까지, 주관식만
	public List<MajorMultipleChoiceQuestion> findAllFetchChoicesByCategoriesAndLevelsApprovedAndShortAnswered(
		List<QuestionCategory> categories, List<QuestionLevel> questionLevels) {
		return majorQuestionRepository.findFetchChoicesWithCategoriesAndLevelsAndIfApprovedAndCanBeShortAnswered(
			categories, questionLevels);
	}

}
