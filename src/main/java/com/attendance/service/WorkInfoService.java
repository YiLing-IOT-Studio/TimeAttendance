package com.attendance.service;

import com.attendance.entity.WorkInfo;

import java.util.Date;
import java.util.List;

/**
 * Created by FantasticPan on 2018/4/27.
 */
public interface WorkInfoService {

    List<WorkInfo> getAllWorkInfo(Date start);

    List<WorkInfo> findWorkInfoByName(String name);

    void addOrUpdate(WorkInfo workInfo);

    void delAllWorkInfoByName(String name);
}
