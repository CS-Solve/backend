package com.comssa.persistence.member.service;


import com.comssa.persistence.member.domain.Member;
import com.comssa.persistence.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberRepositoryService {
	private final MemberRepository memberRepository;

	@Nullable
	public Member findByCognitoId(String cognitoId) {
		return null;
	}

	@NotNull
	public Member save(Member from) {
		return null;
	}
}
