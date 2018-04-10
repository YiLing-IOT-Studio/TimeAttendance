package com.attendance.service;

import com.attendance.entity.Staff;
import com.attendance.mapper.StaffRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by FantasticPan on 2018/4/5.
 */
@Service
public class StaffRoleService {

    @Autowired
    private StaffRoleMapper staffRoleMapper;

    public void addStaff(Staff staff) {
        staffRoleMapper.addStaff(staff);
    }

    public int getRoleId(String role) {
        return staffRoleMapper.getRoleId(role);
    }

    public void addStaffWithRole(int staff_id,int role_id) {
        staffRoleMapper.addStaffWithRole(staff_id,role_id);
    }

    public void delStaffWithRole(int staff_id) {
        staffRoleMapper.delStaffWithRole(staff_id);
        staffRoleMapper.delStaff(staff_id);
    }

    public void delStaff(int staff_id) {
        staffRoleMapper.delStaff(staff_id);
    }
}
