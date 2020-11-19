package com.ssafy.gg.service;

import java.util.List;

import com.ssafy.gg.dto.Card;


public interface CardService {
	
	List<Card> selectAllCard();
	Card selectCardByNo(int card_no);
	int insertCard(Card card);
	int updateCard(Card card);
	int deleteUserByNo(int card_no);
	
}
