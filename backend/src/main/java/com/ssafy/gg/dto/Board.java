package com.ssafy.gg.dto;

public class Board {
	private int board_no;
	private int board_type;
	private String board_title;
	private String board_body;
	private String user_nickname;
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public int getBoard_type() {
		return board_type;
	}
	public void setBoard_type(int board_type) {
		this.board_type = board_type;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_body() {
		return board_body;
	}
	public void setBoard_body(String board_body) {
		this.board_body = board_body;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	@Override
	public String toString() {
		return "Board [board_no=" + board_no + ", board_type=" + board_type + ", board_title=" + board_title
				+ ", board_body=" + board_body + ", user_nickname=" + user_nickname + "]";
	}
}
