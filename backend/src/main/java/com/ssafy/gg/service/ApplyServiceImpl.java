package com.ssafy.gg.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.gg.dao.ApplyDAO;
import com.ssafy.gg.dto.Apply;


@Service
public class ApplyServiceImpl implements ApplyService {
	@Autowired
	private ApplyDAO dao;
	
	@Override
	public List<Apply> selectAllApply(){
		return dao.selectAllApply();
	}
	@Override
	public List<Apply> selectApplyByCardNo(int card_no){
		return dao.selectApplyByCardNo(card_no);
	}
	@Override
	public int insertApply(Apply apply){
		return dao.insertApply(apply);
	}
	@Override
	public int deleteAllByCardNo(int card_no){
		return dao.deleteAllByCardNo(card_no);
	}
	@Override
	public int deleteByCardNoAndUserNickname(int card_no , String user_nickname){
		Map<String, String> apply = new HashMap<String, String>();
		apply.put("card_no", card_no+"");
		apply.put("user_nickname", user_nickname);
		return dao.deleteByCardNoAndUserNickname(apply);
	}
    
}
