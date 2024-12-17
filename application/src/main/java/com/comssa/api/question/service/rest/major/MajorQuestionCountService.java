package com.comssa.api.question.service.rest.major;

import com.comssa.persistence.question.dto.major.response.ResponseMajorQuestionClassCountDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MajorQuestionCountService {
	List<ResponseMajorQuestionClassCountDto> getNormalQuestionCountByClass();
}
