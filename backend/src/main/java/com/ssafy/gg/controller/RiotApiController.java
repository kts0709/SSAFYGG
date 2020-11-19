package com.ssafy.gg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.gg.service.RiotApiService;

import io.swagger.annotations.ApiOperation;

//http://i3a401.p.ssafy.io:8399/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/riot")
public class RiotApiController {

	private static final Logger logger = LoggerFactory.getLogger(RiotApiController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private RiotApiService riotApiService;
	
    @ApiOperation(value = "소환사 기본 정보를 가져오는 API", response = String.class)
	@GetMapping("summoner")
    public ResponseEntity<String> summonerApi(@RequestParam String summonerName, @RequestParam String apiKey) {
        logger.debug("Riot Summoner API");
        
        String summoner = riotApiService.fetchSummoner(summonerName, apiKey);
        
        if (summoner.equals("")) {
        	return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
        } else {
        	return new ResponseEntity<String>(summoner, HttpStatus.OK);
        }
    }
    
    @ApiOperation(value = "소환사의 최근 전적을 가져오는 API", response = String.class)
    @GetMapping("matches")
    public ResponseEntity<String> Matches(@RequestParam String summonerName, @RequestParam String apiKey) {
    	logger.debug("Riot Matches API");
    	
    	String matches = riotApiService.fetchMatches(summonerName, apiKey);
    	
    	if (matches != null) {
    		return new ResponseEntity<String>(matches, HttpStatus.OK);
    	} else {
    		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    	}
    }
}
