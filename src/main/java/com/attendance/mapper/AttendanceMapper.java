package com.attendance.mapper;

import com.attendance.entity.Attendance;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by FantasticPan on 2018/3/18.
 */
@Repository
public interface AttendanceMapper {

    void addAttendanceInTime(Attendance attendance);

    void addAttendanceOutTime(Attendance attendance);

    void updateAttendanceTotalMilli(Attendance attendance);

    List<Attendance> getAttendanceByNameAndDate(String name, Date date1, Date date2);

    Attendance getAttendance(String name);
}
