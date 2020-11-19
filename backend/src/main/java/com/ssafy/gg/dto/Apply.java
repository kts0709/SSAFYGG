package com.ssafy.gg.dto;

import java.util.Date;

public class Apply {
	private int apply_no;
	private int card_no;
	private String user_nickname;
	
	public int getApply_no() {
		return apply_no;
	}
	public void setApply_no(int apply_no) {
		this.apply_no = apply_no;
	}
	public int getCard_no() {
		return card_no;
	}
	public void setCard_no(int card_no) {
		this.card_no = card_no;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	@Override
	public String toString() {
		return "Apply [apply_no=" + apply_no + ", card_no=" + card_no + ", user_nickname=" + user_nickname + "]";
	}
}
