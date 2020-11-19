package com.ssafy.gg.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.gg.dao.CardDAO;
import com.ssafy.gg.dto.Card;


@Service
public class CardServiceImpl implements CardService {
	@Autowired
	private CardDAO dao;
	
	@Override
	public List<Card> selectAllCard(){
		return dao.selectAllCard();
	}
	@Override
	public Card selectCardByNo(int card_no) {
		return dao.selectCardByNo(card_no);
	}
	@Override
	public int insertCard(Card card) {
		return dao.insertCard(card);
	}
	@Override
	public int updateCard(Card card) {
		return dao.updateCard(card);
	}
	@Override
	public int deleteUserByNo(int card_no) {
		return dao.deleteUserByNo(card_no);
	}
    
}
