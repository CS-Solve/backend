package com.server.computer_science.question.normal_question.domain;


import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.common.QuestionLevel;
import com.server.computer_science.question.normal_question.dto.request.RequestMakeNormalQuestionDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class NormalQuestion {
    @Id @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    private String question;
    private QuestionCategory questionCategory;
    private QuestionLevel questionLevel;

    @OneToMany(mappedBy = "normalQuestion",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NormalQuestionChoice> normalQuestionChoices;

    public static NormalQuestion makeWithDto(RequestMakeNormalQuestionDto dto){
        return NormalQuestion.builder()
                .questionCategory(dto.getQuestionCategory())
                .question(dto.getQuestion())
                .questionLevel(dto.getQuestionLevel())
                .build();
    }

    @Builder
    public NormalQuestion(String question, QuestionCategory questionCategory, QuestionLevel questionLevel) {
        this.question = question;
        this.questionCategory = questionCategory;
        this.questionLevel = questionLevel;
        this.normalQuestionChoices = new ArrayList<>();
    }

    public boolean isFit(QuestionCategory questionCategory, QuestionLevel questionLevel){
        return this.questionCategory.equals(questionCategory) && this.questionLevel.equals(questionLevel);
    }
}
