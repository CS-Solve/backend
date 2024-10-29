package com.server.computer_science.question.normal_question.common.domain;


import com.server.computer_science.question.common.domain.Question;
import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.common.domain.QuestionLevel;
import com.server.computer_science.question.normal_question.admin.dto.RequestMakeNormalQuestionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class NormalQuestion extends Question {
    @Id @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    public boolean canBeShortAnswered;
    public boolean ifApproved;

    @OneToMany(mappedBy = "normalQuestion",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionChoice> questionChoices;

    public static NormalQuestion makeWithDto(RequestMakeNormalQuestionDto dto){
        NormalQuestion normalQuestion = NormalQuestion.builder()
                .content(dto.getContent())
                .questionCategory(dto.getQuestionCategory())
                .questionLevel(dto.getQuestionLevel())
                .description(dto.getDescription())
                .imageUrl(null)
                .build();
        normalQuestion.initDefaults();
        return normalQuestion;
    }
    public void initDefaults() {
        this.questionChoices = new ArrayList<>();
        this.canBeShortAnswered = false;
        this.ifApproved = false;
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

    @Override
    public String toString() {
        return "NormalQuestion{" +
                "questionChoices=" + questionChoices +
                ", content='" + content + '\'' +
                ", questionCategory=" + questionCategory +
                ", questionLevel=" + questionLevel +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", ifApproved=" + ifApproved +
                ", canBeShortAnswered=" + canBeShortAnswered +
                '}';
    }
}
