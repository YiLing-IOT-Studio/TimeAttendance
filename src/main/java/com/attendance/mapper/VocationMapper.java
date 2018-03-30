package com.attendance.mapper;

import com.attendance.entity.Vocation;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FantasticPan on 2018/3/26.
 */
@Service
public interface VocationMapper {

    void addVocation(Vocation leave);
    List<Vocation> getVocationByName(String name);
}
