package com.ssafy.gg.dto;

import java.util.Date;

public class Card {
	private int card_no;
	private String card_type;
	private int card_recruit_max;
	private int card_recruit_now;
	private String card_host;
	private String card_guest;
	private boolean card_active;
	private int card_tier_start;
	private int card_tier_end;
	private String card_position;
	private String card_message;
	private Date card_create_time;
	private Date card_matching_time;
	private String card_hashtag;
	private String card_host_rank;
	
	public int getCard_no() {
		return card_no;
	}
	public void setCard_no(int card_no) {
		this.card_no = card_no;
	}
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}
	public int getCard_recruit_max() {
		return card_recruit_max;
	}
	public void setCard_recruit_max(int card_recruit_max) {
		this.card_recruit_max = card_recruit_max;
	}
	public int getCard_recruit_now() {
		return card_recruit_now;
	}
	public void setCard_recruit_now(int card_recruit_now) {
		this.card_recruit_now = card_recruit_now;
	}
	public String getCard_host() {
		return card_host;
	}
	public void setCard_host(String card_host) {
		this.card_host = card_host;
	}
	public String getCard_guest() {
		return card_guest;
	}
	public void setCard_guest(String card_guest) {
		this.card_guest = card_guest;
	}
	public boolean isCard_active() {
		return card_active;
	}
	public void setCard_active(boolean card_active) {
		this.card_active = card_active;
	}
	public int getCard_tier_start() {
		return card_tier_start;
	}
	public void setCard_tier_start(int card_tier_start) {
		this.card_tier_start = card_tier_start;
	}
	public int getCard_tier_end() {
		return card_tier_end;
	}
	public void setCard_tier_end(int card_tier_end) {
		this.card_tier_end = card_tier_end;
	}
	public String getCard_position() {
		return card_position;
	}
	public void setCard_position(String card_position) {
		this.card_position = card_position;
	}
	public String getCard_message() {
		return card_message;
	}
	public void setCard_message(String card_message) {
		this.card_message = card_message;
	}
	public Date getCard_create_time() {
		return card_create_time;
	}
	public void setCard_create_time(Date card_create_time) {
		this.card_create_time = card_create_time;
	}
	public Date getCard_matching_time() {
		return card_matching_time;
	}
	public void setCard_matching_time(Date card_matching_time) {
		this.card_matching_time = card_matching_time;
	}
	public String getCard_hashtag() {
		return card_hashtag;
	}
	public void setCard_hashtag(String card_hashtag) {
		this.card_hashtag = card_hashtag;
	}
	
	public String getCard_host_rank() {
		return card_host_rank;
	}
	public void setCard_host_rank(String card_host_rank) {
		this.card_host_rank = card_host_rank;
	}
	@Override
	public String toString() {
		return "Card [card_no=" + card_no + ", card_type=" + card_type + ", card_recruit_max=" + card_recruit_max
				+ ", card_recruit_now=" + card_recruit_now + ", card_host=" + card_host + ", card_guest=" + card_guest
				+ ", card_active=" + card_active + ", card_tier_start=" + card_tier_start + ", card_tier_end="
				+ card_tier_end + ", card_position=" + card_position + ", card_message=" + card_message
				+ ", card_create_time=" + card_create_time + ", card_matching_time=" + card_matching_time
				+ ", card_hashtag=" + card_hashtag + ", card_host_rank=" + card_host_rank + "]";
	}
}
