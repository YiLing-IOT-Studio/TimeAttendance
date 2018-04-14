package com.attendance;

import com.attendance.repository.StaffRepository;
import com.attendance.service.AttendanceService;
import com.attendance.service.SignStateService;
import com.attendance.service.VocationService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectApplicationTests {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private VocationService vocationService;
    private Gson gson = new Gson();
    @Autowired
    private SignStateService signStateService;
    @Autowired
    private AttendanceService attendanceService;

    @Test
    public void contextLoads() {
    }

}
