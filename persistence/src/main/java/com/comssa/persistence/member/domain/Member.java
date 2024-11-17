package com.comssa.persistence.member.domain;

import com.comssa.persistence.comment.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private boolean ifDeleted;
	private String cognitoId;

	@OneToMany(mappedBy = "member")
	private List<Comment> comments;

	public static Member from(String cognitoId) {
		return Member.builder()
			.cognitoId(cognitoId)
			.ifDeleted(false)
			.comments(new ArrayList<>())
			.build();
	}
}
