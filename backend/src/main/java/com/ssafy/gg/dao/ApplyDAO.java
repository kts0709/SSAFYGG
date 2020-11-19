package com.ssafy.gg.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.gg.dto.Apply;

public interface ApplyDAO {
	List<Apply> selectAllApply();
	List<Apply> selectApplyByCardNo(int card_no);
	int insertApply(Apply apply);
	int deleteAllByCardNo(int card_no);
	int deleteByCardNoAndUserNickname(Map<String, String> apply);
}
