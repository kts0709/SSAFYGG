package com.ssafy.gg.dto;


public class User {
	private String user_type;
	private String user_auth;
	private String user_nickname;
	private int user_match;
	private int user_score;
	private String user_token;
	private int user_report;
	
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public String getUser_auth() {
		return user_auth;
	}
	public void setUser_auth(String user_auth) {
		this.user_auth = user_auth;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public int getUser_match() {
		return user_match;
	}
	public void setUser_match(int user_match) {
		this.user_match = user_match;
	}
	public int getUser_score() {
		return user_score;
	}
	public void setUser_score(int user_score) {
		this.user_score = user_score;
	}
	public String getUser_token() {
		return user_token;
	}
	public void setUser_token(String user_token) {
		this.user_token = user_token;
	}
	
	public int getUser_report() {
		return user_report;
	}
	public void setUser_report(int user_report) {
		this.user_report = user_report;
	}
	@Override
	public String toString() {
		return "User [user_type=" + user_type + ", user_auth=" + user_auth + ", user_nickname=" + user_nickname
				+ ", user_match=" + user_match + ", user_score=" + user_score + ", user_token=" + user_token
				+ ", user_report=" + user_report + "]";
	}
}
