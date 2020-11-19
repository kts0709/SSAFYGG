package com.ssafy.gg.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.gg.service.BoardService;
import com.ssafy.gg.dto.Board;

import io.swagger.annotations.ApiOperation;

//http://i3a401.p.ssafy.io:8399/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private BoardService boardService;

    @ApiOperation(value = "전체 Board 정보를 반환한다.", response = List.class)
	@GetMapping("selectAll")
	public ResponseEntity<List<Board>> selectAllBoard() throws Exception {
		logger.debug("Board - selectAll");
		return new ResponseEntity<List<Board>>(boardService.selectAllBoard(), HttpStatus.OK);
	}

    @ApiOperation(value = "내가 작성한 Board 정보를 반환한다.", response = List.class)    
    @GetMapping("selectByNickname")
	public ResponseEntity<List<Board>> selectBoardByNickname(@RequestParam String user_nickname) throws Exception{
		logger.debug("Board - selectByNickname");
		return new ResponseEntity<List<Board>>(boardService.selectBoardByNickname(user_nickname), HttpStatus.OK);
	}

    @ApiOperation(value = "번호에 따른 Board 정보를 반환한다.", response = Board.class)    
    @GetMapping("selectByNo")
	public ResponseEntity<Board> selectBoardByNo(@RequestParam int board_no) throws Exception{
		logger.debug("Board - selectByNo");
		return new ResponseEntity<Board>(boardService.selectBoardByNo(board_no), HttpStatus.OK);
	}
    
    @ApiOperation(value = "새로운 Board 정보를 입력한다. 그리고 DB입력 성공여부에 따라 1 또는 0을 반환한다.", response = String.class)
	@PostMapping("insert")
	public ResponseEntity<String> insertBoard(@RequestBody Board board) throws Exception{
		logger.debug("Board - insert");
		if (boardService.insertBoard(board) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "해당 Board의 정보를 수정한다. 그리고 DB입력 성공여부에 따라 1 또는 0을 반환한다.", response = String.class)
	@PutMapping("update")
	public ResponseEntity<String> updateBoard(@RequestBody Board board) throws Exception{
		logger.debug("Board - update");
		if (boardService.updateBoard(board) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "해당 Board의 정보를 삭제한다. 그리고 DB입력 성공여부에 따라 1 또는 0을 반환한다.", response = String.class)
	@DeleteMapping("delete")
	public ResponseEntity<String> deleteAlarm(@RequestParam int board_no) throws Exception{
		logger.debug("Board - delete");
		if (boardService.deleteBoard(board_no) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
}