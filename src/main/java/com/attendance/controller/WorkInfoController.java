package com.attendance.controller;

import com.attendance.entity.Staff;
import com.attendance.entity.WorkInfo;
import com.attendance.repository.StaffRepository;
import com.attendance.service.VocationService;
import com.attendance.service.WorkInfoService;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by FantasticPan on 2018/4/3.
 */
@Controller
public class WorkInfoController {

    @Autowired
    private Gson gson;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private VocationService vocationService;
    @Autowired
    private WorkInfoService workInfoService;

    @ResponseBody
    @PostMapping("/work_info")
    public String work_info(HttpServletRequest request) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        String month = request.getParameter("month");
        String year = request.getParameter("year");
        Integer month2 = Integer.valueOf(month);
        Integer year2 = Integer.valueOf(year);
        calendar.set(year2, month2, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = year + "-" + month + "-" + "01";
        String date2 = year + "-" + month + "-" + calendar.get(Calendar.DAY_OF_MONTH);
        Date start = sdf.parse(date1);
        Date end = sdf.parse(date2);
        String leaveDate;
        List<Staff> staffList = staffRepository.findAllStaff("ROLE_USER");
        for (Staff staff : staffList) {
            Integer rest_day = vocationService.getWorkDayByDate(staff.getStaffName(), start, end);
            if (rest_day == null) {
                rest_day = 0;
            }
            List<String> leaveDateList = vocationService.getLeaveDateByName(staff.getStaffName());
            if (leaveDateList.size() == 0) {
                leaveDate = "";
            } else {
                leaveDate = StringUtils.join(leaveDateList, ",");
            }
            String staffNum = String.format("%0" + 3 + "d", staff.getId());
            WorkInfo workInfo = new WorkInfo(staff.getStaffName(), staffNum,
                    calendar.get(Calendar.DAY_OF_MONTH) - rest_day, rest_day, start, leaveDate);
            workInfoService.addOrUpdate(workInfo);
        }
        List<WorkInfo> workInfoList = workInfoService.getAllWorkInfo(start);
        return gson.toJson(workInfoList);
    }

    @ResponseBody
    @PostMapping("/work_info_name")
    public String getWorkInfoByName(HttpServletRequest request) {
        String name = request.getParameter("searchName");
        return gson.toJson(workInfoService.findWorkInfoByName(name));
    }
}
