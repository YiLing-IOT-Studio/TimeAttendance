package com.attendance.repository;

import com.attendance.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by FantasticPan on 2018/3/25.
 */
public interface StaffRepository extends JpaRepository<Staff, Long> {

    Staff findByStaffName(String staff_name);
}
