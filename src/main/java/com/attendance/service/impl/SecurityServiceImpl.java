package com.attendance.service.impl;

import com.attendance.entity.Staff;
import com.attendance.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by FantasticPan on 2018/3/23.
 */
@Service
public class SecurityServiceImpl implements UserDetailsService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public UserDetails loadUserByUsername(String staff_name) {
        Staff staff = staffRepository.findByStaffName(staff_name);
        if (null == staff) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return staff;
    }
}
