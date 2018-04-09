package com.attendance.controller;

import com.attendance.service.StaffRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FantasticPan on 2018/4/9.
 */
@Controller
public class StaffRoleController {

    @Autowired
    private StaffRoleService staffRoleService;

    @ResponseBody
    @PostMapping("/addStaff")
    public void addStaff(HttpServletRequest request) {
        String staffName = request.getParameter("data");
        //Staff staff = new Staff(staffName,staffDate);
        //staffRoleService.addStaff(staff);
        //Integer staff_id = staff.getId();
        //Integer rol_id = staffRoleService.getRoleId("ROLE_USER");
        //staffRoleService.addStaffWithRole(staff_id,rol_id);
        System.out.println(staffName);
    }

    @ResponseBody
    @PostMapping("/delStaff")
    public void delStaff(HttpServletRequest request) {
        String staffId = request.getParameter("staffId");
        Integer staff_id = Integer.valueOf(staffId);
        staffRoleService.delStaffWithRole(staff_id);
    }
}
