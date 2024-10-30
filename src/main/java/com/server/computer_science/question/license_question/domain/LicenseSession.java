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

    @Enumerated(value = EnumType.STRING)
    protected LicenseCategory licenseCategory;

    @OneToMany(mappedBy = "licenseSession",cascade = CascadeType.PERSIST, orphanRemoval = false)
    private List<LicenseNormalQuestion> licenseNormalQuestions;


    @Builder
    public LicenseSession(String content, LicenseCategory licenseCategory) {
        this.content = content;
        this.licenseCategory = licenseCategory;
    }

    public static LicenseSession from(String content, LicenseCategory licenseCategory) {
        return LicenseSession.builder()
                .licenseCategory(licenseCategory)
                .content(content)
                .build();
    }
}
