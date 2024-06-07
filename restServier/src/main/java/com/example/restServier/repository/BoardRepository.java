package com.example.restServier.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restServier.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{ //JpaRepository가 이미 컴포넌트로 등록되어있으므로 상속받는 repository에 따로 처리를 안해도 됨.

}
