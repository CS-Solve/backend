package com.server.computer_science.question.normal_question.service;

import com.server.computer_science.question.normal_question.domain.NormalQuestion;
import com.server.computer_science.question.normal_question.domain.NormalQuestionChoice;
import com.server.computer_science.question.normal_question.dto.request.RequestMakeNormalQuestionDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionDto;
import com.server.computer_science.question.normal_question.repository.NormalQuestionChoiceRepository;
import com.server.computer_science.question.normal_question.repository.NormalQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasicNormalQuestionMakeService implements NormalQuestionMakeService {
    private final NormalQuestionRepository normalQuestionRepository;
    private final NormalQuestionChoiceRepository normalQuestionChoiceRepository;
    private final DuplicateQuestionDetector duplicateQuestionDetector;
    @Override
    @Transactional
    public List<ResponseNormalQuestionDto> makeNormalQuestion(List<RequestMakeNormalQuestionDto> requestNormalQuestionDtos) {
        List<NormalQuestion> normalQuestions = normalQuestionRepository.findAll();
        return requestNormalQuestionDtos
                .stream()
                .filter(normalQuestionDto -> checkAllQuestions(normalQuestionDto, normalQuestions))
                .map(this::makeNormalQuiz)
                .collect(Collectors.toList());
    }

    private boolean checkAllQuestions(RequestMakeNormalQuestionDto normalQuestionDto, List<NormalQuestion> normalQuestions) {
        for(NormalQuestion normalQuestion: normalQuestions){
            if(duplicateQuestionDetector.isQuestionDuplicate(normalQuestion.getQuestion(), normalQuestionDto.getQuestion()))
                return false;
        }
        return true;
    }

    private ResponseNormalQuestionDto makeNormalQuiz(RequestMakeNormalQuestionDto requestNormalQuestionDto) {
        NormalQuestion normalQuestion = NormalQuestion.makeWithDto(requestNormalQuestionDto);
        normalQuestionRepository.save(normalQuestion);
        normalQuestionChoiceRepository.saveAll(requestNormalQuestionDto.getNormalQuestionChoices()
                .stream()
                .map(nqc -> NormalQuestionChoice.makeWithDto(nqc,normalQuestion))
                .collect(Collectors.toList()));
        return ResponseNormalQuestionDto.of(normalQuestion);
    }
}
