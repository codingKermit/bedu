package com.care.bedu.user.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>{
	boolean existsByEmail(String email);
	boolean existsByNickname(String nickname);
	MemberEntity findByEmail(String email);

}
