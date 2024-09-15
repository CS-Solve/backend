package com.server.computer_science.question.normal_question.domain;


import com.server.computer_science.question.common.ProblemCategory;
import com.server.computer_science.question.common.ProblemLevel;
import lombok.Generated;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class NormalQuestion {
    @Id @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    private String question;
    private ProblemCategory problemCategory;
    private ProblemLevel problemLevel;
    
}
