package com.attendance.service;

import com.attendance.entity.SignState;
import com.attendance.mapper.SignStateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FantasticPan on 2018/3/29.
 */
@Service
public class SignStateService {

    @Autowired
    private SignStateMapper signStateMapper;

    public List<SignState> getAllSignState() {
        return signStateMapper.getAllSignState();
    }

    public void addSignState(String name) {
        signStateMapper.addSignState(name);
    }

    public void delSignState(String name) {
        signStateMapper.delSignState(name);
    }

    public void signIn(String name) {
        signStateMapper.signIn(name);
    }

    public void signOut(String name) {
        signStateMapper.signOut(name);
    }
}
