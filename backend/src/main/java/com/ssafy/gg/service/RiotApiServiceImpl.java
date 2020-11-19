package com.ssafy.gg.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ssafy.gg.dto.Champion;
import com.ssafy.gg.dto.Role;

@Service
public class RiotApiServiceImpl implements RiotApiService {
	
	// LOL 버전을 가져오는 URL
	private final static String versionURL = "https://ddragon.leagueoflegends.com/api/versions.json";
	
	// Champion 정보를 가져올 URL을 만들때 사용하는 접두사
	private final static String championURLPrefix = "http://ddragon.leagueoflegends.com/cdn/";
	
	// Champion 정보를 가져올 URL을 만들때 사용하는 접미사
	private final static String championURLSuffix = "/data/en_US/champion.json";
	
	// 최근 경기 정보를 가져올 URL을 만들 때 사용하는 접두사
	private final static String matchURLPrefix = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/";
	
	// 최근 경기 정보를 가져올 URL을 만들 때 사용하는 접미사
	private final static String matchURLSuffix = "?queue=420&endIndex=10&beginIndex=0&api_key=";
	
	// 경기 ID를 통해 경기 결과를 가겨올 URL
	private final static String matchResultURL = "https://kr.api.riotgames.com/lol/match/v4/matches/";
	
	// 각 챔피언별 key값을 저장하기 위한 Map
	private static Map<String, String> map = new HashMap<>();
	
	@Override
	public String fetchSummoner(String summonerName, String apiKey) {
		JsonObject summoner = new JsonObject();
		
		JsonObject summonerInfo = fetchSummonerInfo(summonerName, apiKey);
		summoner.add("summonerLevel", summonerInfo.get("summonerLevel"));
		
		fetchSummonerGameInfo(summonerInfo.get("id").getAsString(), apiKey, summoner);
	    
	    return summoner.toString();
	}
	
	@Override
	public String fetchMatches(String summonerName, String apiKey) {
		// 챔피언 정보를 가져올 URL을 구한다.
		String championURL = fetchChampionURL();
		fetchChampions(championURL);
		
		// 암호화된 id값을 가져와 특정 경기의 사용자 정보를 구한다.
		JsonObject summonerInfo = fetchSummonerInfo(summonerName, apiKey);
		String accountID = summonerInfo.get("accountId").toString();
		accountID = accountID.substring(1, accountID.length() - 1); // "를 제거해야 한다.
		
		return fetchLatestMatches(accountID, apiKey);
	}
	
	// 사용자의 기본정보를 JSON Object로 가져옴.
	private static JsonObject fetchSummonerInfo(String summonerName, String apiKey) {
		// 기본 정보를 담을 JSON Object
		JsonObject summoner = null;
		
		// URL에 사용될 address 작업
		StringBuilder address = new StringBuilder();
		address.append("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/");
		
		for (int i = 0; i < summonerName.length(); i++) {
			char ch = summonerName.charAt(i);
			if (ch != ' ') {
				address.append(ch);
			} else {
				// 공백문자는 %20으로 바뀌어야 한다.
				address.append("%20");
			}
		}
		
		address.append("?api_key=" + apiKey);
		
	    try {
	        URL url = new URL(address.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        
	        // 응답 코드. 200이 아니면 오류가 난 것이다.
	        int responseCode = conn.getResponseCode();
	        System.out.println("summoner response code: " + responseCode);
	        if (responseCode != 200) return null;
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line = "";
	        String result = "";
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        
	        JsonParser jsonParser = new JsonParser();
	        summoner = jsonParser.parse(result).getAsJsonObject();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return summoner;
	}
	
	// 소환사의 랭크정보를 가져오는 메소드
	private static void fetchSummonerGameInfo(String id, String apiKey, JsonObject retJson) {
		StringBuilder address = new StringBuilder("https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/");
		address.append(id);
		address.append("?api_key=" + apiKey);
		
		try {
			URL url = new URL(address.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        
	        System.out.println("league api response: " + conn.getResponseCode());
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line = "";
	        String result = "";
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        
	        JsonParser jsonParser = new JsonParser();
	        JsonArray jsonArray = jsonParser.parse(result).getAsJsonArray();
	        
	        // 솔로랭크가 아닌 것들은 모두 지움
	        for (int i = jsonArray.size() - 1; i >= 0; i--) {
	        	String queueType = jsonArray.get(i).getAsJsonObject().get("queueType").toString();
	        	
	        	if (!queueType.equals("\"RANKED_SOLO_5x5\"")) {
	        		jsonArray.remove(i);
	        	}
	        }
	        
	        retJson.add("rank", jsonArray);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 가장 최신 버전의 챔피언 정보를 가져오는 URL을 리턴해주는 Method
	private static String fetchChampionURL() {
		StringBuilder sb = new StringBuilder();
		
		try {
			URL url = new URL(versionURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			
			System.out.println("version api response: " + conn.getResponseCode());
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";
			while ((line = br.readLine()) != null) {
				result += line;
			}
			
			JsonParser jsonParser = new JsonParser();
			JsonArray jsonArray = jsonParser.parse(result).getAsJsonArray();
			String version = jsonArray.get(0).toString();
			// 맨 앞과 뒤에 있는 따옴표를 제거 해야한다.
			version = version.substring(1, version.length() - 1);
			
			sb.append(championURLPrefix);
			sb.append(version);
			sb.append(championURLSuffix);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	// URL을 통해 챔피언 정보가 있는 JSON을 가져옴. 이를 통해 map을 업데이트 시킴.
	private static void fetchChampions(String championURL) {
		try {
			URL url = new URL(championURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			
			System.out.println("champion info api response: " + conn.getResponseCode());
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";
			while ((line = br.readLine()) != null) {
				result += line;
			}
			
			JsonParser jsonParser = new JsonParser();
			JsonObject data = jsonParser.parse(result).getAsJsonObject().get("data").getAsJsonObject();
			
			for(String key : data.keySet()) {
				JsonObject champion = data.get(key).getAsJsonObject();
				String championKey = champion.get("key").toString(); 
				String championID = champion.get("id").toString();
				map.put(championKey.substring(1, championKey.length() - 1), championID.substring(1, championID.length() - 1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 최근 10경기의 정보를 가져옴.
	private static String fetchLatestMatches(String id, String apiKey) {
		String ret = null;
		
		try {
			URL url = new URL(matchURLPrefix + id + matchURLSuffix + apiKey);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			
			System.out.println("match list api response: " + conn.getResponseCode());
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";
			while ((line = br.readLine()) != null) {
				result += line;
			}
			
			JsonParser jsonParser = new JsonParser();
			JsonArray matches = jsonParser.parse(result).getAsJsonObject().get("matches").getAsJsonArray();
			for (int i = 0; i < matches.size(); i++) {
				JsonObject match = matches.get(i).getAsJsonObject();
				JsonObject matchResult = fetchMatchResult(id, match.get("gameId").toString(), apiKey);
				match.add("win", matchResult.get("win"));
				match.add("kills", matchResult.get("kills"));
				match.add("deaths", matchResult.get("deaths"));
				match.add("assists", matchResult.get("assists"));
				match.addProperty("champion", map.get(match.get("champion").toString()));
			}

			ret = arrangeMostChampion(matches).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	// 경기 ID를 받으면 해당 경기의 승패, KDA를 담은 정보를 리턴
	private static JsonObject fetchMatchResult(String id, String matchID, String apiKey) {
		// JSON에서의 문자열을 찾기 위해 앞뒤로 "를 추가함
		id = "\"" + id + "\"";
		JsonObject matchResult = new JsonObject();
		
		try {
			URL url = new URL(matchResultURL + matchID + "?api_key=" + apiKey);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			
			System.out.println("match result api response: " + conn.getResponseCode());
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";
			while ((line = br.readLine()) != null) {
				result += line;
			}
			
			// 먼저 찾고자 하는 소환사 아이디가 경기중 어떤 참가자 ID로 쓰였는지 확인할 participantID가 필요하다.
			JsonParser jsonParser = new JsonParser();
			JsonObject match = jsonParser.parse(result).getAsJsonObject();
			JsonArray participantIdentities = match.get("participantIdentities").getAsJsonArray();
			
			String participantID = null;
			
			for (int i = 0; i < participantIdentities.size(); i++) {
				JsonObject participant = participantIdentities.get(i).getAsJsonObject();
				JsonObject player = participant.get("player").getAsJsonObject();
				if (id.equals(player.get("accountId").toString())) {
					participantID = participant.get("participantId").toString();
					break;
				}
			}
			
			// 찾은 ID를 통해 승패, KDA를 구한다.
			JsonArray participants = match.get("participants").getAsJsonArray();
			for (int i = 0; i < participants.size(); i++) {
				JsonObject participant = participants.get(i).getAsJsonObject();
				if (participantID.equals(participant.get("participantId").toString())) {
					JsonObject stats = participant.get("stats").getAsJsonObject();
					matchResult.add("win", stats.get("win"));
					matchResult.add("kills", stats.get("kills"));
					matchResult.add("deaths", stats.get("deaths"));					
					matchResult.add("assists", stats.get("assists"));					
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matchResult;
	}
	
	// 라인, 챔피언별 요약 정보 담을 Json Object를 추가할 메소드
	private static JsonObject arrangeMostChampion(JsonArray matches) {
		// 리턴할 object
		JsonObject retObject = new JsonObject();
		
		// 라인 역할군을 담을 배열
		Role[] roles = new Role[6];
		roles[0] = new Role("NONE");
		roles[1] = new Role("TOP");
		roles[2] = new Role("JUG");
		roles[3] = new Role("MID");
		roles[4] = new Role("ADC");
		roles[5] = new Role("SUP");
		
		// 최근 10경기 챔피언 정보를 담을 배열
		List<Champion> list = new LinkedList<>();
		Map<String, Integer> tempMap = new HashMap<>(); // index를 관리해줄 map
		int count = 0;
		
		for (int i = 0; i < matches.size(); i++) {
			JsonObject match = matches.get(i).getAsJsonObject();
			
			// 역할군 정보 요약 작업
			String role = match.get("role").toString();
			String lane = match.get("lane").toString();
			int roleIndex;
			
			if (lane.contains("TOP")) {
				roleIndex = 1;
			} else if (lane.contains("JU")) {
				roleIndex = 2;
			} else if (lane.contains("MID")) {
				roleIndex = 3;
			} else if (lane.contains("CAR") || role.contains("CAR")) {
				roleIndex = 4;
			} else if (lane.contains("SUP") || role.contains("SUP")) {
				roleIndex = 5;
			} else {
				roleIndex = 0;
			}
			
			// 챔피언 정보 요약 작업
			String champion = match.get("champion").toString();
			
			if (!tempMap.containsKey(champion)) {
				tempMap.put(champion, count++);
				list.add(new Champion(champion));
			}
			
			int kills = match.get("kills").getAsInt();
			int deaths = match.get("deaths").getAsInt();
			int assists = match.get("assists").getAsInt();
			int championIndex = tempMap.get(champion);
			
			list.get(championIndex).inputKDA(kills, deaths, assists);
			
			boolean win = match.get("win").getAsBoolean();
			
			if (win) {
				roles[roleIndex].win();
				list.get(championIndex).win();
			}
			else {
				roles[roleIndex].lose();
				list.get(championIndex).lose();
			}
		}
		
		Arrays.sort(roles);
		Collections.sort(list);
		
		for (int i = 0; i < roles.length; i++) {
			System.out.println(roles[i].toString());
		}
		
		JsonArray laneJsonArray = new JsonArray();
		for (int i = 0; i < 2; i++) {
			if (roles[i].getTotalGames() == 0) break;
			
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("role", roles[i].getName());
			jsonObject.addProperty("winRate", roles[i].getRate());
			
			int roleRate = (int) Math.round((double) roles[i].getTotalGames() / (double) matches.size() * 100);
			jsonObject.addProperty("pickRate", roleRate);
			
			laneJsonArray.add(jsonObject);
		}
		
		// 모스트 챔피언 작업
		JsonArray championJsonArray = new JsonArray();
		
		for (int i = 0; i < list.size() && i < 3; i++) {
			JsonObject jsonObject = new JsonObject();
			
			Champion champion = list.get(i);
			String championName = champion.getName();
			jsonObject.addProperty("name", championName.substring(1, championName.length() - 1));
			jsonObject.addProperty("kda", champion.getKDA());
			
			jsonObject.addProperty("winRate", champion.getRate());
			int pickRate = (int) Math.round((double) champion.getTotalGames() / (double) matches.size() * 100);
			jsonObject.addProperty("pickRate", pickRate);
			
			championJsonArray.add(jsonObject);
		}
		
		retObject.add("lane", laneJsonArray);
		retObject.add("champion", championJsonArray);
		retObject.add("matches", matches);
		
		return retObject;
	}
}
