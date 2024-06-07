package com.example.restServier.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restServier.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{

	public Member findByUsernameAndPassword(String username, String password);
}
