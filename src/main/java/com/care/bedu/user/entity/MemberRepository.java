package com.care.bedu.user.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>{
	boolean existsByEmail(String email);
	boolean existsByNickname(String nickname);
	Optional <MemberEntity> findByEmail(String email);

}
