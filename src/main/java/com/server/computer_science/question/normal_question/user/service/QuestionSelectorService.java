package com.server.computer_science.question.normal_question.user.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionSelectorService {
    public List<String> getCategories();
    public List<String> getLevels();
}
