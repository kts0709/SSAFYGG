package com.ssafy.gg.service;

public interface RiotApiService {
	// 소환사 정보를 가져올 메소드.
	public String fetchSummoner(String summonerName, String apiKey);
	// 소환사 최근 전적을 가져올 메소드.
	public String fetchMatches(String summonerName, String apiKey);
}
