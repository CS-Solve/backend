package com.api.computerscience.question.major.user.service;

import com.persistence.computerscience.question.major.domain.user.dto.response.ResponseMajorQuestionClassCountDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MajorQuestionCountService {
	List<ResponseMajorQuestionClassCountDto> getNormalQuestionCountByClass();
}
