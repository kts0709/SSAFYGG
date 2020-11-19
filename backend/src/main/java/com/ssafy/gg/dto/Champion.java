package com.ssafy.gg.dto;

public class Champion implements Comparable<Champion> {
	private String name;
	private int wins = 0;
	private int totalGames = 0;
	private int rate;
	private int kills = 0;
	private int deaths = 0;
	private int assists = 0;
	
	public Champion(String name) {
		this.name = name;
	}
	
	public void inputKDA(int kills, int deaths, int assists) {
		this.kills += kills;
		this.deaths += deaths;
		this.assists += assists;
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
	
	public String getKDA() {
		if (deaths == 0) return "perfect";
		
		double kda = (double) (kills + assists) / (double) deaths;
		
		return String.format("%.2f", kda);
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
	public int compareTo(Champion o) {
		if (this.totalGames == o.totalGames) {
			return o.rate - this.rate;
		}
		return o.totalGames - this.totalGames;
	}

	@Override
	public String toString() {
		return "Champion [name=" + name + ", wins=" + wins + ", totalGames=" + totalGames + ", rate=" + rate
				+ ", kills=" + kills + ", deaths=" + deaths + ", assists=" + assists + "]";
	}
	
}
