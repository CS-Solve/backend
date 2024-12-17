package com.comssa.api.question.service.rest.major;


import com.comssa.api.exception.DuplicateQuestionException;
import com.comssa.persistence.question.domain.major.MajorDescriptiveQuestion;
import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion;
import com.comssa.persistence.question.dto.major.request.RequestMakeMajorDescriptiveQuestionDto;
import com.comssa.persistence.question.dto.major.request.RequestMakeMajorMultipleChoiceQuestionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminMajorQuestionMakeService {

    List<MajorMultipleChoiceQuestion> makeMultipleChoiceQuestions(
            List<RequestMakeMajorMultipleChoiceQuestionDto> requestNormalQuestionDto);

    MajorMultipleChoiceQuestion makeMultipleChoiceQuestion(
            RequestMakeMajorMultipleChoiceQuestionDto requestNormalQuestionDto) throws DuplicateQuestionException;

    List<MajorDescriptiveQuestion> makeDescriptiveQuestions(
            List<RequestMakeMajorDescriptiveQuestionDto> requestNormalQuestionDto
    );
}
