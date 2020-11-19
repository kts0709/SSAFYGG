package com.ssafy.gg.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.gg.dto.User;

public interface UserDAO {
	List<User> selectAllUser();
	List<User> selectUserByTypeAndAuth(Map<String, String> user);
	List<User> selectUserByNickname(String user_nickname);
	int insertUser(User user);
	int updateUser(User user); //유저 업데이트(닉네임변경 , 매칭수 증가 , 싫어요 증가)
	int deleteUserByTypeAndAuth(Map<String, String> user);
	
}
