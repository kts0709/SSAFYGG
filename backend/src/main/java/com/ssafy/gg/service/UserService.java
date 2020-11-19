package com.ssafy.gg.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.gg.dto.User;


public interface UserService {
	
	List<User> selectAllUser();
	List<User> selectUserByTypeAndAuth(String user_type , String user_auth);
	List<User> selectUserByNickname(String user_nickname);
	int insertUser(User user);
	int updateUser(User user);
	int deleteUserByTypeAndAuth(String user_type , String user_auth);
	HashMap<String, Object> getUserInfo_kakao(String access_Token);
	String getUserInfo_naver(String access_Token);
}
