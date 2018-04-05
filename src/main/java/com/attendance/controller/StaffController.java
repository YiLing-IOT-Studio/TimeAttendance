package com.attendance.controller;

import com.attendance.entity.Staff;
import com.attendance.repository.StaffRepository;
import com.attendance.service.StaffRoleService;
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
    @PostMapping("/add_staff")
    public void addStaff(HttpServletRequest request) {
    }

    @ResponseBody
    @PostMapping("/delete_staff")
    public void deleteStaff(HttpServletRequest request) {
    }
}
