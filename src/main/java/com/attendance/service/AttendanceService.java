package com.attendance.service;

import com.attendance.entity.Attendance;
import com.attendance.mapper.AttendanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by FantasticPan on 2018/3/19.
 */
@Service
public class AttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    public void add(Attendance attendanceInfo) {
        attendanceMapper.add(attendanceInfo);
    }
}
