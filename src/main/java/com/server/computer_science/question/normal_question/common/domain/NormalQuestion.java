package com.server.computer_science.question.normal_question.common.domain;


import com.server.computer_science.question.common.domain.Question;
import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.common.domain.QuestionLevel;
import com.server.computer_science.question.normal_question.user.dto.request.RequestMakeNormalQuestionDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class NormalQuestion extends Question {
    @Id @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    public boolean canBeShortAnswered;
    public boolean ifApproved;

    @OneToMany(mappedBy = "normalQuestion",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionChoice> questionChoices;

    public static NormalQuestion makeWithDto(RequestMakeNormalQuestionDto dto){
        return NormalQuestion.builder()
                .questionCategory(dto.getQuestionCategory())
                .content(dto.getQuestion())
                .questionLevel(dto.getQuestionLevel())
                .description(dto.getDescription())
                .build();
    }

    @Builder
    public NormalQuestion(String content, QuestionCategory questionCategory, QuestionLevel questionLevel, String description) {
        this.content = content;
        this.questionCategory = questionCategory;
        this.questionLevel = questionLevel;
        this.questionChoices = new ArrayList<>();
        this.description = description;
    }


    public void toggleApproved(){
        this.ifApproved = !this.ifApproved;
    }
    public void toggleCanBeShortAnswered(){
        this.canBeShortAnswered = !this.canBeShortAnswered;
    }

    public boolean isFit(QuestionCategory questionCategory, QuestionLevel questionLevel){
        return this.questionCategory.equals(questionCategory) && this.questionLevel.equals(questionLevel);
    }

    public static NormalQuestion makeForTest(){
        return NormalQuestion.builder()
                .content("testQuest")
                .questionCategory(QuestionCategory.COMPUTER_ARCHITECTURE)
                .questionLevel(QuestionLevel.LOW)
                .description("testDescription")
                .build();
    }
}
