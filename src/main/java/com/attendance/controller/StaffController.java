package com.attendance.controller;

import com.attendance.entity.Staff;
import com.attendance.repository.StaffRepository;
import com.attendance.service.SignStateService;
import com.attendance.service.StaffRoleService;
import com.attendance.service.WorkInfoService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by FantasticPan on 2018/4/1.
 */
@Controller
public class StaffController {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private Gson gson;
    @Autowired
    private StaffRoleService staffService;
    @Autowired
    private StaffRoleService staffRoleService;
    @Autowired
    private WorkInfoService workInfoService;
    @Autowired
    private SignStateService signStateService;

    @ResponseBody
    @GetMapping("/getStaffByName")
    public String getStaffByName(String name) {
        return gson.toJson(staffRepository.findStaffByName(name));
    }

    @ResponseBody
    @GetMapping("/getAllStaff")
    public String getAllStaff() {
        List<Staff> staffList = staffRepository.findAllStaff("ROLE_USER");
        return gson.toJson(staffList);
    }

    @ResponseBody
    @PostMapping("/addStaff")
    public void addStaff(HttpServletRequest request) {
        String staffName = request.getParameter("staffName");
        String staffDate = request.getParameter("staffDate");
        Staff staff = new Staff(staffName,staffDate);
        staffRoleService.addStaff(staff);
        Integer staff_id = staff.getId();
        Integer rol_id = staffRoleService.getRoleId("ROLE_USER");
        staffRoleService.addStaffWithRole(staff_id,rol_id);
        signStateService.addSignState(staffName);
    }

    @ResponseBody
    @PostMapping("/delStaff")
    public void delStaff(HttpServletRequest request) {
        String staffId = request.getParameter("staffId");
        String staffName = request.getParameter("staffName");
        Integer staff_id = Integer.valueOf(staffId);
        staffRoleService.delStaffWithRole(staff_id);
        workInfoService.delAllWorkInfoByName(staffName);
        signStateService.delSignState(staffName);
    }
}
