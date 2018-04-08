package com.attendance.mapper;

import com.attendance.entity.Staff;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
}
