package com.attendance.controller;

import com.attendance.entity.Attendance;
import com.attendance.service.AttendanceService;
import com.attendance.util.SecurityUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by FantasticPan on 2018/3/30.
 */
@Controller
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private Gson gson;

    @ResponseBody
    @GetMapping("/attendance_list")
    public String attendanceRecords() {
        String name = SecurityUtil.getCurrentUsername();
        List<Attendance> attendanceList = attendanceService.getAttendanceByName(name);
        return gson.toJson(attendanceList);
    }
}
