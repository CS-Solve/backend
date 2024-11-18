package com.comssa.persistence.comment.domain;


import com.comssa.persistence.member.domain.Member;
import com.comssa.persistence.question.common.domain.BaseEntity;
import com.comssa.persistence.question.common.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString(exclude = {"member"})
public class Comment extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;

	private String content;


	public static Comment from(String content, Member member, Question question) {
		return Comment.builder()
			.content(content)
			.member(member)
			.question(question)
			.build();
	}
}
