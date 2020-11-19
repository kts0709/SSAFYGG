package com.ssafy.gg.service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.gg.dao.BoardDAO;
import com.ssafy.gg.dto.Board;


@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO dao;
	
	@Override
	public List<Board> selectAllBoard(){
		return dao.selectAllBoard();
	}
	@Override
	public List<Board> selectBoardByNickname(String user_nickname){
		return dao.selectBoardByNickname(user_nickname);
	}
	@Override
	public Board selectBoardByNo(int board_no){
		return dao.selectBoardByNo(board_no);
	}
	@Override
	public int insertBoard(Board board){
		return dao.insertBoard(board);
	}
	@Override
	public int updateBoard(Board board){
		return dao.updateBoard(board);
	}
	public int deleteBoard(int board_no){
		return dao.deleteBoard(board_no);
	}
	
}
