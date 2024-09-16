package com.server.computer_science.question.normal_question.service;

import com.server.computer_science.question.normal_question.domain.NormalQuestion;
import com.server.computer_science.question.normal_question.domain.NormalQuestionChoice;
import com.server.computer_science.question.normal_question.dto.request.RequestNormalQuestionDto;
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
public class BasicNormalProblemMakeService implements NormalProblemMakeService{
    private final NormalQuestionRepository normalQuestionRepository;
    private final NormalQuestionChoiceRepository normalQuestionChoiceRepository;
    @Override
    @Transactional
    public ResponseNormalQuestionDto makeNormalQuestion(RequestNormalQuestionDto requestNormalQuestionDto) {
        NormalQuestion normalQuestion = NormalQuestion.makeWithDto(requestNormalQuestionDto);
        normalQuestionRepository.save(normalQuestion);
        normalQuestionChoiceRepository.saveAll(requestNormalQuestionDto.getNormalQuestionChoices()
                .stream()
                .map(nqc -> NormalQuestionChoice.makeWithDto(nqc,normalQuestion))
                .collect(Collectors.toList()));
        return ResponseNormalQuestionDto.of(normalQuestion);
    }
}
