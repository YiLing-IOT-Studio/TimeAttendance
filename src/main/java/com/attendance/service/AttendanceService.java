package com.attendance.service;

import com.attendance.entity.Attendance;

import java.util.Date;
import java.util.List;

/**
 * Created by FantasticPan on 2018/4/26.
 */
public interface AttendanceService {

    void addAttendanceInTime(Attendance attendance);

    void addAttendanceOutTime(Attendance attendance);

    void updateAttendanceTotalMilli(Attendance attendance);

    List<Attendance> getAttendanceByNameAndDate(String name, Date date1, Date date2);

    Attendance getAttendance(String name);
}
