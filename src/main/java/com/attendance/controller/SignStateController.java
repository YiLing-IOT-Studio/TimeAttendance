package com.attendance.controller;

import com.attendance.entity.Attendance;
import com.attendance.entity.SignState;
import com.attendance.service.AttendanceService;
import com.attendance.service.SignStateService;
import com.attendance.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by FantasticPan on 2018/3/29.
 */
@RestController
public class SignStateController {

    @Autowired
    private SignStateService signStateService;
    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/allSignState")
    public List<SignState> getAllSignState() {
        return signStateService.getAllSignState();
    }

    // TODO: 2018/4/12 事务
    @PostMapping("/signIn")
    public void in(HttpServletRequest request) {
        String staffName = request.getParameter("staffName");
        signStateService.signIn(staffName);
        Attendance attendance = new Attendance(staffName,new Date(System.currentTimeMillis()));
        attendanceService.addAttendanceInTime(attendance);
    }

    @PostMapping("/signOut")
    public void out(HttpServletRequest request) {
        String staffName = request.getParameter("staffName");
        signStateService.signOut(staffName);
        Attendance attendance = new Attendance(staffName,new Date(System.currentTimeMillis()));
        Attendance attendance_info = attendanceService.getAttendance(staffName);
        attendanceService.addAttendanceOutTime(attendance);
        Attendance attendanceTotalMilli = new Attendance(staffName,
                String.valueOf(TimeUtil.formatTime(attendance_info.getInTime(),attendance.getInTime())));
        attendanceService.updateAttendanceTotalMilli(attendanceTotalMilli);
    }
}
