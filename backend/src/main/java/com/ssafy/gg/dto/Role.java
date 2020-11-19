package com.ssafy.gg.dto;

public class Role implements Comparable<Role> {
	private String name; // 역할군 이름
	private int wins = 0; // 이긴 게임 수
	private int totalGames = 0; // 총 게임 수
	private int rate; // 승률
	
	public Role(String name) {
		this.name = name;
	}
	
	public void win() {
		wins++;
		totalGames++;
		rate = (int) Math.round(((double) wins / (double) totalGames) * 100);
	}
	
	public void lose() {
		totalGames++;
		rate = (int) Math.round(((double) wins / (double) totalGames) * 100);
	}

	public String getName() {
		return name;
	}

	public int getRate() {
		return rate;
	}
	
	public int getTotalGames() {
		return totalGames;
	}

	@Override
	public int compareTo(Role o) {
		if (this.totalGames == o.totalGames) {
			return o.rate - this.rate;
		}
		return o.totalGames - this.totalGames;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + ", wins=" + wins + ", totalGames=" + totalGames + ", rate=" + rate + "]";
	}
	
}
