package com.attendance;

import com.attendance.entity.Vocation;
import com.attendance.repository.StaffRepository;
import com.attendance.repository.WorkRepository;
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
    @Autowired
    private WorkRepository workRepository;

    private Gson gson = new Gson();

    @Test
    public void contextLoads() {

        List<Vocation> vocationList = vocationService.getAllVocationByAdmin("宇智波攀");
        System.out.println(gson.toJson(vocationList));
    }

    @Test
    public void index() {
        System.out.println(gson.toJson(workRepository.findWorkInfoByStaffName("李攀")));
    }

}
