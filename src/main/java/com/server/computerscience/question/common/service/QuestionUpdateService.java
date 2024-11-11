package com.server.computerscience.question.common.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.computerscience.question.common.domain.Question;
import com.server.computerscience.question.common.dto.request.RequestChangeContentDto;

@Service
@Transactional
public interface QuestionUpdateService<T extends Question> {

	// 기본 구현이 필요한 메서드에 default 사용
	default T changeDescription(Long questionId, RequestChangeContentDto requestChangeContentDto) {
		T question = findById(questionId);
		System.out.println(question);
		question.changeDescription(requestChangeContentDto.getContent());
		return question;
	}

	default T changeContent(Long questionId, RequestChangeContentDto requestChangeContentDto) {
		T question = findById(questionId);
		question.changeContent(requestChangeContentDto.getContent());
		return question;
	}

	default T toggleApprove(Long questionId) {
		T question = findById(questionId);
		question.toggleApproved();
		return question;
	}

	void deleteQuestion(Long questionId);

	// 각 구현체에서 구현해야 하는 추상 메서드
	T findById(Long questionId);
}
