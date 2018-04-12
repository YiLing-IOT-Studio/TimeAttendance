package com.attendance;

import com.attendance.entity.SignState;
import com.attendance.repository.StaffRepository;
import com.attendance.service.SignStateService;
import com.attendance.service.VocationService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

    @Test
    public void contextLoads() {
        List<SignState> signStateList = signStateService.getAllSignState();
        System.out.println(signStateList);
    }

    @Test
    public void index() {
    }

}
