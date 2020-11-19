package com.ssafy.gg.dto;


public class Alarm {
	private int alarm_no;
	private String alarm_title;
	private String alarm_body;
	private String alarm_from;
	private String alarm_to;
	private int card_no;
	private int alarm_read;
	
	public int getAlarm_no() {
		return alarm_no;
	}
	public void setAlarm_no(int alarm_no) {
		this.alarm_no = alarm_no;
	}
	public String getAlarm_title() {
		return alarm_title;
	}
	public void setAlarm_title(String alarm_title) {
		this.alarm_title = alarm_title;
	}
	public String getAlarm_body() {
		return alarm_body;
	}
	public void setAlarm_body(String alarm_body) {
		this.alarm_body = alarm_body;
	}
	public String getAlarm_from() {
		return alarm_from;
	}
	public void setAlarm_from(String alarm_from) {
		this.alarm_from = alarm_from;
	}
	public String getAlarm_to() {
		return alarm_to;
	}
	public void setAlarm_to(String alarm_to) {
		this.alarm_to = alarm_to;
	}
	public int getCard_no() {
		return card_no;
	}
	public void setCard_no(int card_no) {
		this.card_no = card_no;
	}
	public int getAlarm_read() {
		return alarm_read;
	}
	public void setAlarm_read(int alarm_read) {
		this.alarm_read = alarm_read;
	}
	@Override
	public String toString() {
		return "Alarm [alarm_no=" + alarm_no + ", alarm_title=" + alarm_title + ", alarm_body=" + alarm_body
				+ ", alarm_from=" + alarm_from + ", alarm_to=" + alarm_to + ", card_no=" + card_no + ", alarm_read="
				+ alarm_read + "]";
	}
}
