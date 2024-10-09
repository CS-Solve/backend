package com.server.computer_science.question.admin.service.implement;


import com.server.computer_science.question.admin.service.AdminNormalQuestionGetService;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionDtoForAdmin;
import com.server.computer_science.question.normal_question.service.implement.NormalQuestionDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasicAdminNormalQuestionGetService implements AdminNormalQuestionGetService {
    private final NormalQuestionDBService normalQuestionDBService;

    @Override
    public List<ResponseNormalQuestionDto> getAllNormalQuestionForAdmin() {
        return normalQuestionDBService.findAllFetchChoices()
                .stream()
                .map(ResponseNormalQuestionDtoForAdmin::forAdmin)
                .collect(Collectors.toList());
    }
}
