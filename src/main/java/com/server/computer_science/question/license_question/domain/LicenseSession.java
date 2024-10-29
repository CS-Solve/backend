package com.server.computer_science.question.license_question.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class LicenseSession {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    private String content;

    @OneToMany(mappedBy = "licenseSession",cascade = CascadeType.PERSIST, orphanRemoval = false)
    private List<LicenseNormalQuestion> licenseNormalQuestions;


    @Builder
    public LicenseSession(String content) {
        this.content = content;
    }

    public LicenseSession from(String content) {
        return LicenseSession.builder()
                .content(content)
                .build();
    }
}
