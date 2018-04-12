//package com.attendance.controller;
//
//import com.attendance.service.AttendanceService;
//import com.google.gson.Gson;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Created by FantasticPan on 2018/4/12.
// */
//@Controller
//public class AttendanceController {
//
//    @Autowired
//    private AttendanceService attendanceService;
//    @Autowired
//    private Gson gson;
//
//    @GetMapping("/getAttendanceByNameAndDate")
//    public String getAttendanceByNameAndDate(HttpServletRequest request) {
//        String startDate = request.getParameter("startDate");
//        String endDate = request.getParameter("endDate");
//    }
//}
