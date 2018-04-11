package com.attendance.mapper;

import com.attendance.entity.WorkInfo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by FantasticPan on 2018/4/3.
 */
@Service
public interface WorkInfoMapper {

    List<WorkInfo> getAllWorkInfo(Date start);

    List<WorkInfo> findWorkInfoByName(String name);

    void addOrUpdate(WorkInfo workInfo);

    void delAllWorkInfoByName(String name);
}
