package com.attendance.mapper;

import com.attendance.entity.WorkInfo;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by FantasticPan on 2018/4/3.
 */
@Repository
public interface WorkInfoMapper {

    List<WorkInfo> getAllWorkInfo(Date start);

    List<WorkInfo> findWorkInfoByName(String name);

    void addOrUpdate(WorkInfo workInfo);

    void delAllWorkInfoByName(String name);
}
