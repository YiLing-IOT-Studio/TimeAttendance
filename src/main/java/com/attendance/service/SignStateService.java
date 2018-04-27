package com.attendance.service;

import com.attendance.entity.SignState;

import java.util.List;

/**
 * Created by FantasticPan on 2018/4/26.
 */
public interface SignStateService {

    List<SignState> getAllSignState();

    void addSignState(String name);

    void delSignState(String name);

    void signIn(String name);

    void signOut(String name);
}
