package com.attendance.service.impl;

import com.attendance.entity.SignState;
import com.attendance.mapper.SignStateMapper;
import com.attendance.service.SignStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FantasticPan on 2018/4/26.
 */
@Service
public class SignStateServiceImpl implements SignStateService {

    @Autowired
    private SignStateMapper signStateMapper;

    @Override
    public List<SignState> getAllSignState() {
        return signStateMapper.getAllSignState();
    }

    @Override
    public void addSignState(String name) {
        signStateMapper.addSignState(name);
    }

    @Override
    public void delSignState(String name) {
        signStateMapper.delSignState(name);
    }

    @Override
    public void signIn(String name) {
        signStateMapper.signIn(name);
    }

    @Override
    public void signOut(String name) {
        signStateMapper.signOut(name);
    }
}
