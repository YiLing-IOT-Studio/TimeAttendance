package com.attendance.service;

import com.attendance.entity.Staff;

/**
 * Created by FantasticPan on 2018/4/27.
 */
public interface StaffRoleService {

    void addStaff(Staff staff);

    int getRoleId(String role);

    void addStaffWithRole(int staffId, int roleId);

    void delStaffWithRole(int staffId);

    void delStaff(int staffId);

    void uploadImgUrl(Staff staff);

    String viewImg(String name);
}
