package com.attendance.repository;

import com.attendance.entity.WorkInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by FantasticPan on 2018/4/1.
 */
public interface WorkRepository extends JpaRepository<WorkInfo,Long> {

    List<WorkInfo> findWorkInfoByStaffName(String name);
}
