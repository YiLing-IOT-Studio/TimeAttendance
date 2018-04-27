package com.attendance.service.impl;

import com.attendance.entity.WorkInfo;
import com.attendance.mapper.WorkInfoMapper;
import com.attendance.service.WorkInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by FantasticPan on 2018/4/27.
 */
@Service
public class WorkInfoServiceImpl implements WorkInfoService {

    @Autowired
    private WorkInfoMapper workInfoMapper;

    @Override
    public List<WorkInfo> getAllWorkInfo(Date start) {
        return workInfoMapper.getAllWorkInfo(start);
    }

    @Override
    public List<WorkInfo> findWorkInfoByName(String name) {
        return workInfoMapper.findWorkInfoByName(name);
    }

    @Override
    public void addOrUpdate(WorkInfo workInfo) {
        workInfoMapper.addOrUpdate(workInfo);
    }

    @Override
    public void delAllWorkInfoByName(String name) {
        workInfoMapper.delAllWorkInfoByName(name);
    }
}
