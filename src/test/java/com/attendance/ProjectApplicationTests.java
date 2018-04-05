package com.attendance;

import com.attendance.entity.Staff;
import com.attendance.repository.StaffRepository;
import com.attendance.service.StaffRoleService;
import com.attendance.service.VocationService;
import com.attendance.service.WorkInfoService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectApplicationTests {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private VocationService vocationService;
    @Autowired
    private WorkInfoService workInfoService;
    @Autowired
    private StaffRoleService staffRoleService;


    private Gson gson = new Gson();

    @Test
    public void contextLoads() throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf2.format(new Date());
        System.out.println(time);
        String ff = sdf2.format(new Date());
        System.out.println(sdf1.parse(ff));
        if (ff.endsWith("00:00:00")) {
            ff = ff.substring(0,10);
            System.out.println(ff);
        }
    }

    @Test
    public void index() {
        System.out.println(staffRoleService.getRoleId("ROLE_USER"));
        Staff staff = new Staff("dsdght","ddwd","dwqd");
        int role_id = staffRoleService.getRoleId("ROLE_USER");
        staffRoleService.addStaff(staff);
        int staff_id =staff.getId();
        staffRoleService.addStaffWithRole(staff_id,role_id);
    }

}
