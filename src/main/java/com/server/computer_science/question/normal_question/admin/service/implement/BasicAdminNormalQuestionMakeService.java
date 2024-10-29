package com.server.computer_science.question.normal_question.admin.service.implement;

import com.server.computer_science.question.normal_question.admin.service.AdminNormalQuestionMakeService;
import com.server.computer_science.question.normal_question.common.domain.NormalQuestion;
import com.server.computer_science.question.normal_question.admin.dto.RequestMakeNormalQuestionDto;
import com.server.computer_science.question.normal_question.user.dto.response.ResponseNormalQuestionDto;
import com.server.computer_science.question.normal_question.common.exception.DuplicateQuestionException;
import com.server.computer_science.question.normal_question.common.repository.NormalQuestionRepository;
import com.server.computer_science.question.normal_question.admin.service.DuplicateQuestionDetector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BasicAdminNormalQuestionMakeService implements AdminNormalQuestionMakeService {
    private final NormalQuestionRepository normalQuestionRepository;
    private final NormalQuestionChoiceService normalQuestionChoiceService;
    private final DuplicateQuestionDetector duplicateQuestionDetector;

    /**
     * 리스트로 생성
     */
    @Override
    public List<ResponseNormalQuestionDto> makeNormalQuestions(List<RequestMakeNormalQuestionDto> requestNormalQuestionDtos) {
        List<NormalQuestion> normalQuestions = normalQuestionRepository.findAll();
        return requestNormalQuestionDtos
                .stream()
                .filter(normalQuestionDto -> checkWithAllQuestionsFromDB(normalQuestionDto, normalQuestions))
                .map(this::makeNormalQuiz)
                .collect(Collectors.toList());
    }


    private ResponseNormalQuestionDto makeNormalQuiz(RequestMakeNormalQuestionDto requestNormalQuestionDto) {
        NormalQuestion normalQuestion = NormalQuestion.makeWithDto(requestNormalQuestionDto);
        normalQuestionRepository.save(normalQuestion);
        normalQuestionChoiceService.saveWith(requestNormalQuestionDto, normalQuestion);
        return ResponseNormalQuestionDto.forAdmin(normalQuestion);
    }

    /*
    단일 생성
     */
    @Override
    public ResponseNormalQuestionDto makeNormalQuestion(RequestMakeNormalQuestionDto requestNormalQuestionDto) throws DuplicateQuestionException {
        List<NormalQuestion> normalQuestions = normalQuestionRepository.findAll();
        if(checkWithAllQuestionsFromDB(requestNormalQuestionDto, normalQuestions)){
            throw new DuplicateQuestionException();
        }
        return makeNormalQuiz(requestNormalQuestionDto);
    }
    private boolean checkWithAllQuestionsFromDB(RequestMakeNormalQuestionDto normalQuestionDto, List<NormalQuestion> normalQuestions) {
        for(NormalQuestion normalQuestion: normalQuestions){
            if(duplicateQuestionDetector.isQuestionDuplicate(normalQuestion.getContent(), normalQuestionDto.getContent()))
                return false;
        }
        return true;
    }


}
