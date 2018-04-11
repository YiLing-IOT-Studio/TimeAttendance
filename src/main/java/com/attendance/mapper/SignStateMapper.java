package com.attendance.mapper;

import com.attendance.entity.SignState;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FantasticPan on 2018/3/29.
 */
@Service
public interface SignStateMapper {

    List<SignState> getAllSignState();

    void addSignState(String name);

    void delSignState(String name);
}
