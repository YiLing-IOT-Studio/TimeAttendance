package com.attendance.service.impl;

import com.attendance.entity.Attendance;
import com.attendance.mapper.AttendanceMapper;
import com.attendance.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by FantasticPan on 2018/4/26.
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public void addAttendanceInTime(Attendance attendanceInfo) {
        attendanceMapper.addAttendanceInTime(attendanceInfo);
    }

    @Override
    public void addAttendanceOutTime(Attendance attendanceInfo) {
        attendanceMapper.addAttendanceOutTime(attendanceInfo);
    }

    @Override
    public void updateAttendanceTotalMilli(Attendance attendance) {
        attendanceMapper.updateAttendanceTotalMilli(attendance);
    }

    @Override
    public List<Attendance> getAttendanceByNameAndDate(String name, Date date1, Date date2) {
        return attendanceMapper.getAttendanceByNameAndDate(name, date1, date2);
    }

    @Override
    public Attendance getAttendance(String name) {
        return attendanceMapper.getAttendance(name);
    }
}
