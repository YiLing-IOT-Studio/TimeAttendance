package com.attendance.service;

import com.attendance.entity.WorkInfo;
import com.attendance.mapper.WorkInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by FantasticPan on 2018/4/3.
 */
@Service
public class WorkInfoService {

    @Autowired
    private WorkInfoMapper workInfoMapper;

    public void addWorkInfo(WorkInfo workInfo) {
        workInfoMapper.addWorkInfo(workInfo);
    }

    public List<WorkInfo> getAllWorkInfo(Date start) {
        return workInfoMapper.getAllWorkInfo(start);
    }
}
