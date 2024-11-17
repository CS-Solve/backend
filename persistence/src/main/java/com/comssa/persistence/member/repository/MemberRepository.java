package com.comssa.persistence.member.repository;

import com.comssa.persistence.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByCognitoId(String cognitoId);
}
