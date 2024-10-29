package com.server.computer_science.question.license_question.domain;

import com.server.computer_science.question.common.domain.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@SuperBuilder
@NoArgsConstructor
public abstract class LicenseQuestion extends Question {
    @ManyToOne
    @JoinColumn(name ="license_session_id")
    private LicenseSession licenseSession;
}
