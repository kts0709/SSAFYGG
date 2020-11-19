package com.ssafy.gg.service;

import java.util.List;

import com.ssafy.gg.dto.Board;


public interface BoardService {
	
	List<Board> selectAllBoard();
	List<Board> selectBoardByNickname(String user_nickname);
	Board selectBoardByNo(int board_no);
	int insertBoard(Board board);
	int updateBoard(Board board);
	int deleteBoard(int board_no);
	
}
