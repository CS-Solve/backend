package com.comssa.persistence.member.service;


import com.comssa.persistence.member.domain.Member;
import com.comssa.persistence.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberRepositoryService {
	private final MemberRepository memberRepository;

	@Nullable
	public Member findByCognitoId(String cognitoId) {
		return memberRepository.findByCognitoId(cognitoId).orElse(null);
	}

	@Nullable
	public Member findByCognitoIdFetchJoinComments(String cognitoId) {
		return memberRepository.findByCognitoIdFetchJoinComments(cognitoId).orElse(null);
	}

	public Member save(Member member) {
		return memberRepository.save(member);
	}
}
