package com.attendance.service.impl;

import com.attendance.entity.Staff;
import com.attendance.mapper.StaffRoleMapper;
import com.attendance.service.StaffRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by FantasticPan on 2018/4/27.
 */
@Service
public class StaffRoleServiceImpl implements StaffRoleService {

    @Autowired
    private StaffRoleMapper staffRoleMapper;

    @Override
    public void addStaff(Staff staff) {
        staffRoleMapper.addStaff(staff);
    }

    @Override
    public int getRoleId(String role) {
        return staffRoleMapper.getRoleId(role);
    }

    @Override
    public void addStaffWithRole(int staff_id,int role_id) {
        staffRoleMapper.addStaffWithRole(staff_id, role_id);
    }

    @Override
    public void delStaffWithRole(int staff_id) {
        staffRoleMapper.delStaffWithRole(staff_id);
        staffRoleMapper.delStaff(staff_id);
    }

    @Override
    public void delStaff(int staff_id) {
        staffRoleMapper.delStaff(staff_id);
    }
}
