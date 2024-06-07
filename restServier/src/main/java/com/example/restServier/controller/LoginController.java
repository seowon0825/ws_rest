package com.example.restServier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restServier.entity.Member;
import com.example.restServier.repository.MemberRepository;

@CrossOrigin("*")
@RestController
public class LoginController {

	@Autowired
	private MemberRepository memberRepository;
	
	@PostMapping("/loginProc")
	public ResponseEntity<String> loginProc(@RequestParam("username") String username, @RequestParam("password") String password) {
		
		//DB에서 아이디, 패스워드 있는지 확인
		Member member = memberRepository.findByUsernameAndPassword(username, password);
		if(member != null) {
			return ResponseEntity.ok("로그인 성공");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
		}
	}
	
}
