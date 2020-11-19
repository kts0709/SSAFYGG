package com.ssafy.gg.service;

import java.util.List;

import com.ssafy.gg.dto.Alarm;


public interface AlarmService {
	
	List<Alarm> selectAllAlarm();
	List<Alarm> selectAllAlarmByAlarmTo(String alarm_to);
	Alarm selectAlarmByAlarmNo(int alarm_no);
	int insertAlarm(Alarm alarm);
	int updateAlarm(int alarm_no);
	int deleteAlarm(int alarm_no);
}
