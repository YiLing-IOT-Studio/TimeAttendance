package com.attendance.controller;

import com.attendance.entity.Attendance;
import com.attendance.entity.SignState;
import com.attendance.service.AttendanceService;
import com.attendance.service.SignStateService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by FantasticPan on 2018/3/29.
 */
@Controller
public class SignStateController {

    @Autowired
    private SignStateService signStateService;
    @Autowired
    private Gson gson;
    @Autowired
    private AttendanceService attendanceService;

    @ResponseBody
    @GetMapping("/allSignState")
    public String getAllSignState() {
        List<SignState> signStateList = signStateService.getAllSignState();
        return gson.toJson(signStateList);
    }

    // TODO: 2018/4/12 事务
    @ResponseBody
    @PostMapping("/signIn")
    public void in(HttpServletRequest request) {
        String staffName = request.getParameter("staffName");
        signStateService.signIn(staffName);
        Attendance attendance = new Attendance(staffName,new Date(System.currentTimeMillis()));
        attendanceService.addAttendanceInTime(attendance);
        System.out.println(attendance.getId());
    }

    @ResponseBody
    @PostMapping("/signOut")
    public void out(HttpServletRequest request) {
        String staffName = request.getParameter("staffName");
        signStateService.signOut(staffName);
        Attendance attendance = new Attendance(staffName,new Date(System.currentTimeMillis()));
        attendanceService.addAttendanceOutTime(attendance);
    }
}
