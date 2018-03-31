package com.attendance.service;

import com.attendance.entity.Vocation;
import com.attendance.mapper.VocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FantasticPan on 2018/3/26.
 */
@Service
public class VocationService {

    @Autowired
    private VocationMapper vocationMapper;

    public void addVocation(Vocation leave) {
        vocationMapper.addVocation(leave);
    }

    public List<Vocation> getAllVocationByApplicant(String applicant_name) {
        return vocationMapper.getAllVocationByApplicant(applicant_name);
    }

    public void vocationReadState(Integer id) {
        vocationMapper.vocationReadState(id);
    }

    public void deleteVocation(Integer id) {
        vocationMapper.deleteVocation(id);
    }
}
