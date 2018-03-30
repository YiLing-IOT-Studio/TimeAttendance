package com.attendance.mapper;

import com.attendance.entity.Attendance;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FantasticPan on 2018/3/18.
 */
@Service
public interface AttendanceMapper {

    void add(Attendance attendance);
    List<Attendance> getAttendanceByName(String name);
}
