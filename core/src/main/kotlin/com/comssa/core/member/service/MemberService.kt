package com.comssa.core.member.service

import com.comssa.persistence.member.domain.Member
import com.comssa.persistence.member.service.MemberRepositoryService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(
	private val memberRepositoryService: MemberRepositoryService,
) {
	fun findAndSaveMember(cognitoId: String): Member {
		val existingMember = memberRepositoryService.findByCognitoId(cognitoId)
		return existingMember ?: memberRepositoryService.save(
			Member.from(cognitoId),
		)
	}
}
