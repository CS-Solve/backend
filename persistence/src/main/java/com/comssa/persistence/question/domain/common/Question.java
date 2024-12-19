package com.comssa.persistence.question.domain.common;

import com.comssa.persistence.BaseEntity;
import com.comssa.persistence.comment.domain.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
@NoArgsConstructor
@DiscriminatorColumn
public abstract class Question extends BaseEntity {
    public boolean ifApproved;
    protected String content;
    @Enumerated(value = EnumType.STRING)
    protected QuestionCategory questionCategory;
    @Enumerated(value = EnumType.STRING)
    protected QuestionLevel questionLevel;
    protected String description;
    protected String imageUrl;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "question_id")
    private Long id;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public Question(String content, QuestionCategory questionCategory, QuestionLevel questionLevel, String description,
                    String imageUrl) {
        this.content = content;
        this.questionCategory = questionCategory;
        this.questionLevel = questionLevel;
        this.description = description;
        this.imageUrl = imageUrl;
        this.comments = new ArrayList<>();
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void toggleApproved() {
        this.ifApproved = !this.ifApproved;
    }

    public void updateImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public abstract void initDefaults();
}
