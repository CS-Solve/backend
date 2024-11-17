package com.comssa.persistence.member.service;


import com.comssa.persistence.member.domain.Member;
import com.comssa.persistence.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberRepositoryService {
	private final MemberRepository memberRepository;

	public Member findByCognitoId(String cognitoId) {
		return memberRepository.findByCognitoId(cognitoId).orElse(null);
	}

	public Member save(Member member) {
		return memberRepository.save(member);
	}
}
