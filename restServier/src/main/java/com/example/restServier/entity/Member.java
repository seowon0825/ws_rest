package com.example.restServier.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Member {
	@Id
	private String username;
	private String password;
	private String name;
	private String role;
}
