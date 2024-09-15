package com.server.computer_science.question.normal_question.domain;


import com.server.computer_science.question.common.ProblemCategory;
import com.server.computer_science.question.common.ProblemLevel;
import com.server.computer_science.question.normal_question.dto.request.RequestNormalQuestionDto;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
public class NormalQuestion {
    @Id @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    private String question;
    private ProblemCategory problemCategory;
    private ProblemLevel problemLevel;

    @OneToMany(mappedBy = "normalEvent",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NormalQuestionChoice> normalQuestionChoices;

    public static NormalQuestion makeWithDto(RequestNormalQuestionDto dto){
        return NormalQuestion.builder()
                .problemCategory(dto.getProblemCategory())
                .question(dto.getQuestion())
                .problemLevel(dto.getProblemLevel())
                .build();
    }

    @Builder
    public NormalQuestion(String question, ProblemCategory problemCategory, ProblemLevel problemLevel) {
        this.question = question;
        this.problemCategory = problemCategory;
        this.problemLevel = problemLevel;
    }
}
