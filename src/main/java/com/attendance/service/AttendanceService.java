package com.attendance.service;

import com.attendance.entity.Attendance;
import com.attendance.mapper.AttendanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by FantasticPan on 2018/3/19.
 */
@Service
public class AttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    public void addAttendanceInTime(Attendance attendanceInfo) {
        attendanceMapper.addAttendanceInTime(attendanceInfo);
    }

    public void addAttendanceOutTime(Attendance attendanceInfo) {
        attendanceMapper.addAttendanceOutTime(attendanceInfo);
    }

    public List<Attendance> getAttendanceByNameAndDate(String name, Date date1, Date date2) {
        return attendanceMapper.getAttendanceByNameAndDate(name, date1, date2);
    }

    public Attendance getAttendance(String name) {
        return attendanceMapper.getAttendance(name);
    }

    public void updateAttendanceTotalMilli(Attendance attendance) {
        attendanceMapper.updateAttendanceTotalMilli(attendance);
    }
}
