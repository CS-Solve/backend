package com.server.computer_science.question.admin.service.implement;


import com.server.computer_science.question.admin.service.AdminNormalQuestionGetService;
import com.server.computer_science.question.normal_question.service.implement.NormalQuestionDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasicAdminNormalQuestionGetService implements AdminNormalQuestionGetService {
    private final NormalQuestionDBService normalQuestionDBService;

}
