package com.ssafy.gg.dao;

import java.util.List;


import com.ssafy.gg.dto.Board;


public interface BoardDAO {
	
	List<Board> selectAllBoard();
	List<Board> selectBoardByNickname(String user_nickname);
	Board selectBoardByNo(int board_no);
	int insertBoard(Board board);
	int updateBoard(Board board);
	int deleteBoard(int board_no);
	
}
