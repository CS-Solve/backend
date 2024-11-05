package com.server.computer_science.question.major_question.admin.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.computer_science.question.common.service.Implements.QuestionChoiceService;
import com.server.computer_science.question.major_question.admin.dto.RequestMakeMultipleChoiceQuestionDto;
import com.server.computer_science.question.major_question.admin.service.AdminMajorQuestionMakeService;
import com.server.computer_science.question.major_question.admin.service.DuplicateQuestionDetector;
import com.server.computer_science.question.major_question.common.domain.MajorMultipleChoiceQuestion;
import com.server.computer_science.question.major_question.common.exception.DuplicateQuestionException;
import com.server.computer_science.question.major_question.common.repository.MajorQuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BasicAdminMajorQuestionMakeService implements AdminMajorQuestionMakeService {

	private final MajorQuestionRepository majorQuestionRepository;
	private final QuestionChoiceService questionChoiceService;
	private final DuplicateQuestionDetector duplicateQuestionDetector;

	/**
	 * 리스트로 생성
	 */
	@Override
	public List<MajorMultipleChoiceQuestion> makeMultipleChoiceQuestions(
		List<RequestMakeMultipleChoiceQuestionDto> requestDtos) {
		// 중복되지 않은 질문을 필터링하여 저장
		return requestDtos.stream()
			.filter(this::isNotDuplicateQuestion)
			.map(this::saveMajorMultipleChoiceQuestion)
			.collect(Collectors.toList());
	}

	/**
	 * 단일 생성
	 */
	@Override
	public MajorMultipleChoiceQuestion makeMultipleChoiceQuestion(
		RequestMakeMultipleChoiceQuestionDto requestDto) throws DuplicateQuestionException {
		if (!isNotDuplicateQuestion(requestDto)) {
			throw new DuplicateQuestionException();
		}
		return saveMajorMultipleChoiceQuestion(requestDto);
	}

	/**
	 * 중복되지 않은 질문인지 확인하는 메서드
	 * 매번 DB에서 새롭게 조회 후 검증한다.(DTO 자체의 중복된 데이터)
	 */
	private boolean isNotDuplicateQuestion(RequestMakeMultipleChoiceQuestionDto requestDto) {
		return majorQuestionRepository.findAll().stream()
			.noneMatch(existingQuestion -> duplicateQuestionDetector.isQuestionDuplicate(
				existingQuestion.getContent(), requestDto.getContent()));
	}

	/**
	 * 새로운 질문을 저장하고 선택지를 저장하는 메서드
	 */
	private MajorMultipleChoiceQuestion saveMajorMultipleChoiceQuestion(
		RequestMakeMultipleChoiceQuestionDto requestDto) {
		MajorMultipleChoiceQuestion question = MajorMultipleChoiceQuestion.makeWithDto(requestDto);
		majorQuestionRepository.save(question);
		questionChoiceService.saveWith(requestDto, question);
		return question;
	}
}

