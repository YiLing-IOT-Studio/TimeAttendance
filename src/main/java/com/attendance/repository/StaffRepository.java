package com.attendance.repository;

import com.attendance.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by FantasticPan on 2018/3/25.
 */
public interface StaffRepository extends JpaRepository<Staff, Long> {

    Staff findByStaffName(String staff_name);

    //@Query("select s.staffName from Staff s join s.roles r where r.role=?1")
    //String findByRole(String role);

    @Query("select s.staffName from Staff s join s.roles r where r.role=?1")
    List<String> findByRole(String role);
}
