package com.server.computerscience.question.common.service;

import org.springframework.stereotype.Service;

import com.server.computerscience.question.common.dto.request.RequestQuestionCommandDto;

@Service
public interface QuestionExternalService {
	public void sendQuestionToExternal(RequestQuestionCommandDto requestQuestionCommandDto);
}
