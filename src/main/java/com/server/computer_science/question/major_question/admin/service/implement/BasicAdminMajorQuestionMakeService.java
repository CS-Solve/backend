package com.server.computer_science.question.major_question.admin.service.implement;

import com.server.computer_science.question.common.dto.response.ResponseQuestionDto;
import com.server.computer_science.question.common.service.Implements.QuestionChoiceService;
import com.server.computer_science.question.major_question.admin.service.AdminMajorQuestionMakeService;
import com.server.computer_science.question.major_question.common.domain.MajorMultipleChoiceQuestion;
import com.server.computer_science.question.major_question.admin.dto.RequestMakeMajorMultipleChoiceQuestionDto;
import com.server.computer_science.question.common.dto.response.ResponseQuestionDto;
import com.server.computer_science.question.major_question.common.exception.DuplicateQuestionException;
import com.server.computer_science.question.major_question.common.repository.MajorQuestionRepository;
import com.server.computer_science.question.major_question.admin.service.DuplicateQuestionDetector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BasicAdminMajorQuestionMakeService implements AdminMajorQuestionMakeService {
    private final MajorQuestionRepository majorQuestionRepository;
    private final QuestionChoiceService questionChoiceService;
    private final DuplicateQuestionDetector duplicateQuestionDetector;

    /**
     * 리스트로 생성
     */
    @Override
    public List<ResponseQuestionDto> makeMultipleChoiceQuestions(List<RequestMakeMajorMultipleChoiceQuestionDto> requestNormalQuestionDtos) {
        List<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestions = majorQuestionRepository.findAll();
        return requestNormalQuestionDtos
                .stream()
                .filter(normalQuestionDto -> checkWithAllQuestionsFromDB(normalQuestionDto, majorMultipleChoiceQuestions))
                .map(this::makeNormalQuiz)
                .collect(Collectors.toList());
    }


    private ResponseQuestionDto makeNormalQuiz(RequestMakeMajorMultipleChoiceQuestionDto requestNormalQuestionDto) {
        MajorMultipleChoiceQuestion majorMultipleChoiceQuestion = MajorMultipleChoiceQuestion.makeWithDto(requestNormalQuestionDto);
        majorQuestionRepository.save(majorMultipleChoiceQuestion);
        questionChoiceService.saveWith(requestNormalQuestionDto, majorMultipleChoiceQuestion);
        return ResponseQuestionDto.forAdmin(majorMultipleChoiceQuestion);
    }

    /*
    단일 생성
     */
    @Override
    public ResponseQuestionDto makeMultipleChoiceQuestion(RequestMakeMajorMultipleChoiceQuestionDto requestNormalQuestionDto) throws DuplicateQuestionException {
        List<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestions = majorQuestionRepository.findAll();
        if(checkWithAllQuestionsFromDB(requestNormalQuestionDto, majorMultipleChoiceQuestions)){
            throw new DuplicateQuestionException();
        }
        return makeNormalQuiz(requestNormalQuestionDto);
    }
    private boolean checkWithAllQuestionsFromDB(RequestMakeMajorMultipleChoiceQuestionDto normalQuestionDto, List<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestions) {
        for(MajorMultipleChoiceQuestion majorMultipleChoiceQuestion : majorMultipleChoiceQuestions){
            if(duplicateQuestionDetector.isQuestionDuplicate(majorMultipleChoiceQuestion.getContent(), normalQuestionDto.getContent()))
                return false;
        }
        return true;
    }


}
