package com.attendance.mapper;

import com.attendance.entity.SignState;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by FantasticPan on 2018/3/29.
 */@Repository
public interface SignStateMapper {

    List<SignState> getAllSignState();

    void addSignState(String name);

    void delSignState(String name);

    void signIn(String name);

    void signOut(String name);
}
