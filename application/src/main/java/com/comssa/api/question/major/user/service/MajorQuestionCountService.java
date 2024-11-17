package com.comssa.api.question.major.user.service;

import com.comssa.persistence.question.major.user.dto.response.ResponseMajorQuestionClassCountDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MajorQuestionCountService {
	List<ResponseMajorQuestionClassCountDto> getNormalQuestionCountByClass();
}
