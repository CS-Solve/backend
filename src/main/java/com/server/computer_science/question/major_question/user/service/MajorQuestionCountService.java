package com.server.computer_science.question.major_question.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.server.computer_science.question.major_question.user.dto.response.ResponseMajorQuestionClassCountDto;

@Service
public interface MajorQuestionCountService {
	public List<ResponseMajorQuestionClassCountDto> getNormalQuestionCountByClass();
}
