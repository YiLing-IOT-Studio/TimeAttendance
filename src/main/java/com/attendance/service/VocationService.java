package com.attendance.service;

import com.attendance.entity.Vocation;
import com.attendance.mapper.VocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by FantasticPan on 2018/3/26.
 */
@Service
public class VocationService {

    @Autowired
    private VocationMapper vocationMapper;

    public void addVocation(Vocation vocation) {
        vocationMapper.addVocation(vocation);
    }

    public List<Vocation> getAllVocationByApplicant(String applicant_name) {
        return vocationMapper.getAllVocationByApplicant(applicant_name);
    }

    public List<Vocation> getAllVocationByAdmin(String admin_name) {
        return vocationMapper.getAllVocationByAdmin(admin_name);
    }

    public void vocationReadState(Integer id) {
        vocationMapper.vocationReadState(id);
    }

    public void deleteVocation(Integer id) {
        vocationMapper.deleteVocation(id);
    }

    public void handleVocation(Integer id, String result) {
        vocationMapper.handleVocation(id, result);
    }

    public Integer getWorkDayByDate(String name, Date start, Date end) {
        return vocationMapper.getWorkDayByDate(name, start, end);
    }

    public List<String> getVocationNameByDate(Date start, Date end) {
        return vocationMapper.getVocationNameByDate(start, end);
    }

    public List<String> getLeaveDateByName(String name) {
        return vocationMapper.getLeaveDateByName(name);
    }

    public Vocation getVocationByContent(Integer id) {
        return vocationMapper.getVocationByContent(id);
    }
}
