package com.attendance.service;

import com.attendance.entity.Staff;
import com.attendance.mapper.StaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by FantasticPan on 2018/3/19.
 */
@Service
public class StaffService {

    @Autowired
    private StaffMapper staffMapper;

    public void add(Staff staff) {
        staffMapper.add(staff);
    }
}
