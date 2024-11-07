package com.server.computerscience.question.major.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.server.computerscience.question.major.user.dto.response.ResponseMajorQuestionClassCountDto;

@Service
public interface MajorQuestionCountService {
	List<ResponseMajorQuestionClassCountDto> getNormalQuestionCountByClass();
}
