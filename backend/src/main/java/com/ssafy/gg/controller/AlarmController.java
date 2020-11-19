package com.ssafy.gg.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.gg.service.AlarmService;
import com.ssafy.gg.dto.Alarm;

import io.swagger.annotations.ApiOperation;

//http://i3a401.p.ssafy.io:8399/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/alarm")
public class AlarmController {

	private static final Logger logger = LoggerFactory.getLogger(AlarmController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private AlarmService alarmService;
	
	@ApiOperation(value = "모든  Alarm 정보를 반환한다.", response = List.class)
	@GetMapping("selectAllAlarm")
	public ResponseEntity<List<Alarm>> selectAllAlarm() throws Exception {
		logger.debug("Alarm - selectAllAlarm");
		return new ResponseEntity<List<Alarm>>(alarmService.selectAllAlarm(), HttpStatus.OK);
	}

    @ApiOperation(value = "나에게 온 모든  Alarm 정보를 반환한다.", response = List.class)
	@GetMapping("selectAll")
	public ResponseEntity<List<Alarm>> selectAllAlarmByAlarmTo(@RequestParam String alarm_to) throws Exception {
		logger.debug("Alarm - selectAll");
		return new ResponseEntity<List<Alarm>>(alarmService.selectAllAlarmByAlarmTo(alarm_to), HttpStatus.OK);
	}

    @ApiOperation(value = "해당 번호의 Alarm 정보를 반환한다.", response = Alarm.class)    
    @GetMapping("select")
	public ResponseEntity<Alarm> selectAlarmByAlarmNo(@RequestParam int alarm_no) throws Exception{
		logger.debug("Alarm - select");
		return new ResponseEntity<Alarm>(alarmService.selectAlarmByAlarmNo(alarm_no), HttpStatus.OK);
	}

    @ApiOperation(value = "새로운 Alarm 정보를 입력한다. 그리고 DB입력 성공여부에 따라 1 또는 0을 반환한다.", response = String.class)
	@PostMapping("insert")
	public ResponseEntity<String> insertAlarm(@RequestBody Alarm alarm) throws Exception{
		logger.debug("Alarm - insert");
		if (alarmService.insertAlarm(alarm) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "해당 Alarm의 정보를 읽음으로 수정한다. 그리고 DB입력 성공여부에 따라 1 또는 0을 반환한다.", response = String.class)
	@PutMapping("update")
	public ResponseEntity<String> updateAlarm(@RequestParam int alarm_no) throws Exception{
		logger.debug("Alarm - update");
		if (alarmService.updateAlarm(alarm_no) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "해당 Alarm의 정보를 삭제한다. 그리고 DB입력 성공여부에 따라 1 또는 0을 반환한다.", response = String.class)
	@DeleteMapping("delete")
	public ResponseEntity<String> deleteAlarm(@RequestParam int alarm_no) throws Exception{
		logger.debug("Alarm - delete");
		if (alarmService.deleteAlarm(alarm_no) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
}