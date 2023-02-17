package com.green.nowon.domain.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberEntityRepository extends JpaRepository<MemberEntity, Long> {
	// 쿼리 메서드 유형 : 문법에 맞지 않으면 오류
	Optional<MemberEntity> findByEmail(String username);
	//social로그인
	Optional<MemberEntity> findByEmailAndSocial(String username, boolean social);
	
	boolean existsByEmail(String email);
	boolean existsByNickName(String nickName);
	
	
}