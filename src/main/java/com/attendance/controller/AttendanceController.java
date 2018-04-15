package com.attendance.controller;

import com.attendance.entity.Attendance;
import com.attendance.entity.AttendanceInfo;
import com.attendance.service.AttendanceService;
import com.attendance.util.DateUtil;
import com.attendance.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FantasticPan on 2018/4/12.
 */
@RestController
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/getAttendanceByNameAndDate")
    public List<AttendanceInfo> getAttendanceByNameAndDate(HttpServletRequest request) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String name = SecurityUtil.getCurrentUsername();
        List<Attendance> attendanceList = attendanceService.getAttendanceByNameAndDate(name,
                DateUtil.formatDate(startDate), DateUtil.formatDate(endDate));
        List<AttendanceInfo> attendanceInfoList = new ArrayList<>();
        for (Attendance attendance : attendanceList) {
            attendanceInfoList.add(new AttendanceInfo(attendance.getId(),
                    sdf.format(attendance.getInTime()),
                    sdf.format(attendance.getOutTime()), attendance.getTotalMilli()));
        }
        return attendanceInfoList;
    }
}
