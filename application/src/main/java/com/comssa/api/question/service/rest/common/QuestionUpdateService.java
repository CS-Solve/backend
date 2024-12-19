package com.comssa.api.question.service.rest.common;


import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.dto.common.request.RequestChangeQuestionContentDto;
import com.comssa.persistence.question.dto.common.request.RequestChangeQuestionDescriptionDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
@Transactional
public interface QuestionUpdateService<T extends Question> {

	// 기본 구현이 필요한 메서드에 default 사용
	default T changeDescription(
		Long questionId, RequestChangeQuestionDescriptionDto requestChangeQuestionDescriptionDto) {
		T question = findById(questionId);
		question.changeDescription(requestChangeQuestionDescriptionDto.getDescription());
		return question;
	}

	default T changeContent(Long questionId, RequestChangeQuestionContentDto requestChangeQuestionContentDto) {
		T question = findById(questionId);
		question.changeContent(requestChangeQuestionContentDto.getContent());
		return question;
	}

	default T toggleApprove(Long questionId) {
		T question = findById(questionId);
		question.toggleApproved();
		return question;
	}

	String updateImage(Long questionId, MultipartFile image);

	void deleteQuestion(Long questionId);

	// 각 구현체에서 구현해야 하는 추상 메서드
	T findById(Long questionId);
}
