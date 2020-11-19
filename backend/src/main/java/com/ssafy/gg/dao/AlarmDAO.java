package com.ssafy.gg.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.gg.dto.Alarm;
import com.ssafy.gg.dto.User;

public interface AlarmDAO {
	
	List<Alarm> selectAllAlarm();
	List<Alarm> selectAllAlarmByAlarmTo(String alarm_to);
	Alarm selectAlarmByAlarmNo(int alarm_no);
	int insertAlarm(Alarm alarm);
	int updateAlarm(int alarm_no);
	int deleteAlarm(int alarm_no);
	
}
