package com.ssafy.gg.service;

import java.util.List;

import com.ssafy.gg.dto.Apply;


public interface ApplyService {
	
	List<Apply> selectAllApply();
	List<Apply> selectApplyByCardNo(int card_no);
	int insertApply(Apply apply);
	int deleteAllByCardNo(int card_no);
	int deleteByCardNoAndUserNickname(int card_no , String user_nickname);
	
}
