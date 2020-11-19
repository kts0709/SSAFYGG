package com.ssafy.gg.controller;


import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.gg.dto.Card;
import com.ssafy.gg.service.CardService;

import io.swagger.annotations.ApiOperation;

//http://i3a401.p.ssafy.io:8399/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/card")
public class CardController {

	private static final Logger logger = LoggerFactory.getLogger(CardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private CardService cardService;

    @ApiOperation(value = "모든 Card 정보를 반환한다.", response = List.class)
	@GetMapping("selectAll")
	public ResponseEntity<List<Card>> selectAllCard() throws Exception {
		logger.debug("Card - selectAll");
		return new ResponseEntity<List<Card>>(cardService.selectAllCard(), HttpStatus.OK);
	}

    @ApiOperation(value = "No 에 해당하는 Card의 정보를 반환한다.", response = Card.class)    
    @GetMapping("select")
	public ResponseEntity<Card> selectCardByNo(@RequestParam int card_no) throws Exception{
		logger.debug("Card - select");
		return new ResponseEntity<Card>(cardService.selectCardByNo(card_no), HttpStatus.OK);
	}

    @ApiOperation(value = "새로운 Card 정보를 입력한다. 그리고 DB입력 성공여부에 따라 1 또는 0을 반환한다.", response = String.class)
	@PostMapping("insert")
	public ResponseEntity<String> insertCard(@RequestBody Card card) throws Exception{
		logger.debug("Card - insert");
		if (cardService.insertCard(card) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "해당  Card의 정보를 수정한다. 그리고 DB입력 성공여부에 따라 1 또는 0을 반환한다.", response = String.class)
	@PutMapping("update")
	public ResponseEntity<String> updateCard(@RequestBody Card card) throws Exception{
		logger.debug("Card - update");
		if (cardService.updateCard(card) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "해당 Card의 정보를 삭제한다. 그리고 DB입력 성공여부에 따라 1 또는 0을 반환한다.", response = String.class)
	@DeleteMapping("delete")
	public ResponseEntity<String> deleteCard(@RequestParam int card_no) throws Exception{
		logger.debug("Card - delete");
		if (cardService.deleteUserByNo(card_no) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
}