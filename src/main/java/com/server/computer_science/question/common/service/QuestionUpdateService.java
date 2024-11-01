package com.server.computer_science.question.common.service;

import com.server.computer_science.question.common.domain.Question;
import com.server.computer_science.question.common.dto.request.RequestChangeContentDto;
import com.server.computer_science.question.common.dto.request.RequestChangeDescriptionDto;

public interface QuestionUpdateService<T extends Question> {

    // 기본 구현이 필요한 메서드에 default 사용
    default T changeDescription(Long questionId, RequestChangeDescriptionDto requestChangeDescriptionDto) {
        T question = findById(questionId);
        question.changeDescription(requestChangeDescriptionDto.getDescription());
        return question;
    }

    default T changeContent(Long questionId, RequestChangeContentDto requestChangeContentDto) {
        T question = findById(questionId);
        question.changeContent(requestChangeContentDto.getContent());
        return question;
    }

    void deleteQuestion(Long questionId);

    // 각 구현체에서 구현해야 하는 추상 메서드
    T findById(Long questionId);
}
