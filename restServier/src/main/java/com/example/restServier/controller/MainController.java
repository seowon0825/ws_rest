package com.example.restServier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restServier.entity.Member;
import com.example.restServier.repository.MemberRepository;

@CrossOrigin("*")
@RestController
public class MainController {

	@Autowired
	private MemberRepository memberRepository;
	
	@PostMapping("/joinProc")
	public ResponseEntity<String> joinProc(Member member) {
		member.setRole("ROLE_MEMBER");
		
		//DB에 저장
		Member result = memberRepository.save(member);
		
		if(result != null) {
			return ResponseEntity.ok("등록 성공");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("등록 실패");
		}
	}
	
}
