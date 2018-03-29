package com.attendance.controller;

import com.attendance.entity.Vocation;
import com.attendance.service.VocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FantasticPan on 2018/3/26.
 */
@Controller
public class VocationController {

    @Autowired
    private VocationService vocationService;

    @PostMapping("/leave")
    public void leave(HttpServletRequest request) {
        String applicant = request.getParameter("applicant");
        String leave_days = request.getParameter("days");
        String leave_date = request.getParameter("date");
        String leave_reason = request.getParameter("why");
        Vocation vocation = new Vocation(applicant,leave_days,leave_date,leave_reason);
        vocationService.addLeave(vocation);
    }
}
