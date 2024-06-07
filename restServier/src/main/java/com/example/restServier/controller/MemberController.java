package com.example.restServier.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.restServier.dto.BoardDto;
import com.example.restServier.entity.Board;
import com.example.restServier.entity.Member;
import com.example.restServier.repository.BoardRepository;

import net.coobird.thumbnailator.Thumbnails;

@CrossOrigin("*")
@Controller
@RequestMapping("/member")
public class MemberController {

	@Value("${spring.servlet.multipart.location}")
	private String uploadPath; //저장되는 경로 설정
	
//	@Autowired
	private BoardRepository boardRepository;
	
	public MemberController(BoardRepository boardRepository) { //생성자로 
		this.boardRepository = boardRepository;
	}
	
	
	@PostMapping("/regBoardProc")
	public @ResponseBody ResponseEntity<String> regBoardProc(BoardDto boardDto) {
		Board board = new Board();
		board.setTitle(boardDto.getTitle());
		board.setContent(boardDto.getContent());
		//작성자 데이터의 타입불일치 해결
		Member member = new Member();
		member.setUsername(boardDto.getWriter());
		
		board.setMember(member);
		
		//파일 관련 멤버변수 세팅
		String originName = boardDto.getFileName();
		board.setOriginName(originName);
		String newName = UUID.randomUUID().toString() + originName; //중복되지 않는 새이름 생성
		board.setNewName(newName);
		
		String thumbNailName = "";
		
		//파일처리
		File file = new File(newName);
		
		try{
			boardDto.getFile().transferTo(file);
			System.out.println("파일 업로드 성공....");
			
			//썸네일 생성
			String thumbnailName = "s_" + newName;
			board.setThumbnailName(thumbnailName);
			File thumbfile = new File(uploadPath + thumbnailName);
			File ufile = new File(uploadPath + newName);
			
			Thumbnails.of(ufile).size(100, 100).toFile(thumbfile);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//db에 board(엔티티)를 저장
		Board b = boardRepository.save(board);
		if(b != null) {
			return ResponseEntity.ok("등록 성공");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("등록 실패");
		}
		
	}
	
	//게시물 조회
	//@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> list= boardRepository.findAll();
		model.addAttribute("boardList", list);
		return "member/boardList";
	}
	
	//@GetMapping("/detail")
	public String detail(@RequestParam("bno") int bno, Model model) {
		Optional<Board> result = boardRepository.findById(bno);
		
		Board board = result.get();
		
		model.addAttribute("board", board);
		return "member/getBoard";
	}
	
	
	
	
	
	
	
	
	
	
	
}
