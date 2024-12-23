package com.comssa.core.member.service

import com.comssa.persistence.member.domain.Member
import com.comssa.persistence.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(
	private val memberRepository: MemberRepository,
) {
	fun findAndSaveMember(cognitoId: String): Member {
		val existingMember = memberRepository.findByCognitoId(cognitoId)
		if (!existingMember.isPresent) {
			return memberRepository.save(Member.from(cognitoId))
		}
		return existingMember.get()
	}
}
