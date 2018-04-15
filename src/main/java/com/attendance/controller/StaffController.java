package com.attendance.controller;

import com.attendance.entity.Staff;
import com.attendance.repository.StaffRepository;
import com.attendance.service.SignStateService;
import com.attendance.service.StaffRoleService;
import com.attendance.service.WorkInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by FantasticPan on 2018/4/1.
 */
@RestController
public class StaffController {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private StaffRoleService staffService;
    @Autowired
    private StaffRoleService staffRoleService;
    @Autowired
    private WorkInfoService workInfoService;
    @Autowired
    private SignStateService signStateService;

    @GetMapping("/getStaffByName")
    public Staff getStaffByName(String name) {
        return staffRepository.findStaffByName(name);
    }

    @GetMapping("/getAllStaff")
    public List<Staff> getAllStaff() {
        return staffRepository.findAllStaff("ROLE_USER");
    }

    @PostMapping("/addStaff")
    public void addStaff(Staff staff) {
        staffRoleService.addStaff(staff);
        Integer staff_id = staff.getId();
        Integer rol_id = staffRoleService.getRoleId("ROLE_USER");
        staffRoleService.addStaffWithRole(staff_id,rol_id);
        signStateService.addSignState(staff.getStaffName());
    }

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
