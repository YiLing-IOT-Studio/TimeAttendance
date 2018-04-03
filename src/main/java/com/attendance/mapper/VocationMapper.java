package com.attendance.mapper;

import com.attendance.entity.Vocation;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by FantasticPan on 2018/3/26.
 */
@Service
public interface VocationMapper {

    void addVocation(Vocation vocation);

    Long getVocationNumByApplicant(String applicant_name);

    List<Vocation> getAllVocationByApplicant(String applicant_name);

    List<Vocation> getAllVocationByAdmin(String admin_name);

    void vocationReadState(Integer id);

    void agreeVocation(Integer id);

    void disagreeVocation(Integer id);

    void deleteVocation(Integer id);

    Integer getWorkDayByDate(Date start, Date end, String name);

    List<String> getVocationNameByDate(Date start, Date end);
}
