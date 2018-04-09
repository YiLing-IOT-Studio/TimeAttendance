package com.attendance.mapper;

import com.attendance.entity.Staff;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

/**
 * Created by FantasticPan on 2018/4/5.
 */
@Service
public interface StaffRoleMapper {

    @Insert("INSERT INTO staff(staff_name,password,staff_date) " +
            "VALUES (#{staff.staffName},#{staff.password},#{staff.staffDate})")
    @Options(useGeneratedKeys = true, keyProperty = "staff.id")
    void addStaff(@Param("staff") Staff staff);

    //@Select("SELECT r.id FROM role r WHERE role = #{role} ")
    @Select("SELECT id FROM role WHERE role = #{role} ")
    int getRoleId(String role);

    @Insert("INSERT INTO staff_roles(staff_id,roles_id) VALUES (#{staff_id},#{role_id})")
    void addStaffWithRole(@Param("staff_id") int staffId, @Param("role_id") int roleId);

    @Delete("DELETE FROM staff_roles WHERE staff_id = #{staff_id}")
    void delStaffWithRole(@Param("staff_id") int staffId);

    @Delete("DELETE FROM staff WHERE id = #{staff_id}")
    void delStaff(@Param("staff_id") int staffId);
}
