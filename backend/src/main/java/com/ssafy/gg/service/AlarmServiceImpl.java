package com.ssafy.gg.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.gg.dao.AlarmDAO;
import com.ssafy.gg.dto.Alarm;



@Service
public class AlarmServiceImpl implements AlarmService {
	@Autowired
	private AlarmDAO dao;
	
	@Override
	public List<Alarm> selectAllAlarm(){
		return dao.selectAllAlarm();
	}
	@Override
	public List<Alarm> selectAllAlarmByAlarmTo(String alarm_to){
		return dao.selectAllAlarmByAlarmTo(alarm_to);
	}
	@Override
	public Alarm selectAlarmByAlarmNo(int alarm_no){
		return dao.selectAlarmByAlarmNo(alarm_no);
	}
	@Override
	public int insertAlarm(Alarm alarm){
		return dao.insertAlarm(alarm);
	}
	@Override
	public int updateAlarm(int alarm_no){
		return dao.updateAlarm(alarm_no);
	}
	@Override
	public int deleteAlarm(int alarm_no){
		return dao.deleteAlarm(alarm_no);
	}
    
}
