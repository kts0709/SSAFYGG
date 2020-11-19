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

import com.ssafy.gg.dto.User;
import com.ssafy.gg.service.UserService;

import io.swagger.annotations.ApiOperation;

//http://i3a401.p.ssafy.io:8399/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private UserService userService;

    @ApiOperation(value = "모든 User 정보를 반환한다.", response = List.class)
	@GetMapping("selectAll")
	public ResponseEntity<List<User>> selectAllUser() throws Exception {
		logger.debug("User - selectAll");
		return new ResponseEntity<List<User>>(userService.selectAllUser(), HttpStatus.OK);
	}

    @ApiOperation(value = "Type과 Auth에 해당하는 User의 정보를 반환한다.", response = User.class)    
    @GetMapping("select")
	public ResponseEntity<List<User>> selectUserByTypeAndAuth(@RequestParam String user_type, @RequestParam String user_auth) throws Exception{
		logger.debug("User - select");
		return new ResponseEntity<List<User>>(userService.selectUserByTypeAndAuth(user_type, user_auth), HttpStatus.OK);
	}

    @ApiOperation(value = "Nickname에 해당하는 User의 정보를 반환한다.", response = User.class)    
    @GetMapping("select2")
	public ResponseEntity<List<User>> selectUserByNickname(@RequestParam String user_nickname) throws Exception{
		logger.debug("User - select2");
		return new ResponseEntity<List<User>>(userService.selectUserByNickname(user_nickname), HttpStatus.OK);
	}
    
    @ApiOperation(value = "새로운 User 정보를 입력한다. 그리고 DB입력 성공여부에 따라 1 또는 0을 반환한다.", response = String.class)
	@PostMapping("insert")
	public ResponseEntity<String> insertUser(@RequestBody User user) throws Exception{
		logger.debug("User - insert");
		System.out.println(user.toString());
		if (userService.insertUser(user) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "해당 User의 정보를 수정한다. 그리고 DB입력 성공여부에 따라 1 또는 0을 반환한다.", response = String.class)
	@PutMapping("update")
	public ResponseEntity<String> updateUser(@RequestBody User user) throws Exception{
		logger.debug("User - update");
		if (userService.updateUser(user) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "해당 User의 정보를 삭제한다. 그리고 DB입력 성공여부에 따라 1 또는 0을 반환한다.", response = String.class)
	@DeleteMapping("delete")
	public ResponseEntity<String> deleteUser(@RequestParam String user_type, @RequestParam String user_auth) throws Exception{
		logger.debug("User - delete");
		if (userService.deleteUserByTypeAndAuth(user_type, user_auth) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "카카오 고유닉네임을 받아온다.", response = String.class)
	@GetMapping("kakao")
    public ResponseEntity<String> login_kakao(@RequestParam("code") String code) throws Exception{
        logger.debug("User - kakao");
        HashMap<String, Object> userInfo = userService.getUserInfo_kakao(code);
        //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
        if (userInfo.get("nickname") != null) {
            String nickname = (String)userInfo.get("nickname");
            System.out.println(nickname);
            return new ResponseEntity<String>(nickname, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }
    
    @ApiOperation(value = "네이버 고유닉네임을 받아온다.", response = String.class)
	@GetMapping("naver")
    public ResponseEntity<String> login_naver(@RequestParam("code") String code) throws Exception{
        logger.debug("User - naver");
        String userInfo = userService.getUserInfo_naver(code);
        //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
        if (userInfo != null) return new ResponseEntity<String>(userInfo, HttpStatus.OK);
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }
    
}