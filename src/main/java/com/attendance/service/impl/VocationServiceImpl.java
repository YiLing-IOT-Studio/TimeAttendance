package com.attendance.service.impl;

import com.attendance.entity.Vocation;
import com.attendance.mapper.VocationMapper;
import com.attendance.service.VocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by FantasticPan on 2018/4/27.
 */
@Service
public class VocationServiceImpl implements VocationService {

    @Autowired
    private VocationMapper vocationMapper;

    @Override
    public void addVocation(Vocation vocation) {
        vocationMapper.addVocation(vocation);
    }

    @Override
    public List<Vocation> getAllVocationByApplicant(String applicant_name) {
        return vocationMapper.getAllVocationByApplicant(applicant_name);
    }

    @Override
    public List<Vocation> getAllVocationByAdmin(String admin_name) {
        return vocationMapper.getAllVocationByAdmin(admin_name);
    }

    @Override
    public void vocationReadState(Integer id) {
        vocationMapper.vocationReadState(id);
    }

    @Override
    public void handleVocation(Integer id, String result) {
        vocationMapper.handleVocation(id, result);
    }

    @Override
    public void deleteVocation(Integer id) {
        vocationMapper.deleteVocation(id);
    }

    @Override
    public Integer getWorkDayByDate(String name, Date start, Date end) {
        return vocationMapper.getWorkDayByDate(name, start, end);
    }

    @Override
    public List<String> getVocationNameByDate(Date start, Date end) {
        return vocationMapper.getVocationNameByDate(start, end);
    }

    @Override
    public List<String> getLeaveDateByName(String name) {
        return vocationMapper.getLeaveDateByName(name);
    }

    @Override
    public Vocation getVocationByContent(Integer id) {
        return vocationMapper.getVocationByContent(id);
    }
}
