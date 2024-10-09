package com.server.computer_science.question.normal_question.common.domain;


import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.common.QuestionLevel;
import com.server.computer_science.question.normal_question.admin.dto.RequestMakeNormalQuestionDto;
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
    @Enumerated(value = EnumType.STRING)
    private QuestionCategory questionCategory;
    @Enumerated(value = EnumType.STRING)
    private QuestionLevel questionLevel;
    private String description;

    public boolean canBeShortAnswered;
    public boolean ifApproved;

    @OneToMany(mappedBy = "normalQuestion",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NormalQuestionChoice> normalQuestionChoices;

    public static NormalQuestion makeWithDto(RequestMakeNormalQuestionDto dto){
        return NormalQuestion.builder()
                .questionCategory(dto.getQuestionCategory())
                .question(dto.getQuestion())
                .questionLevel(dto.getQuestionLevel())
                .description(dto.getDescription())
                .build();
    }

    @Builder
    public NormalQuestion(String question, QuestionCategory questionCategory, QuestionLevel questionLevel,String description) {
        this.question = question;
        this.questionCategory = questionCategory;
        this.questionLevel = questionLevel;
        this.normalQuestionChoices = new ArrayList<>();
        this.description = description;
    }

    public void makeQuestionApproved(){
        this.ifApproved = true;
    }
    public void makeQuestionCanBeShortAnswered(){
        this.canBeShortAnswered = true;
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
                .question("testQuest")
                .questionCategory(QuestionCategory.COMPUTER_ARCHITECTURE)
                .questionLevel(QuestionLevel.LOW)
                .description("testDescription")
                .build();
    }
}
