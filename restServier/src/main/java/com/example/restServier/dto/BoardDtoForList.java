package com.example.restServier.dto;

import com.example.restServier.entity.Member;

import lombok.Data;

@Data
public class BoardDtoForList {
	private Integer bno;
	private String title;
	private String content;
	private Member member; 
	private String originName;
	private String newName;
	private String thumbnailName;
}
