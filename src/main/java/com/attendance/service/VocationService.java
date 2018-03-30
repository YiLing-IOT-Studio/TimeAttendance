package com.attendance.service;

import com.attendance.entity.Vocation;
import com.attendance.mapper.VocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by FantasticPan on 2018/3/26.
 */
@Service
public class VocationService {

    @Autowired
    private VocationMapper leaveMapper;

    public void addLeave(Vocation leave) {
        leaveMapper.addVocation(leave);
    }
}
