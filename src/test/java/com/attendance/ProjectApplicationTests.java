package com.attendance;

import com.attendance.repository.StaffRepository;
import com.attendance.service.VocationService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectApplicationTests {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private VocationService vocationService;

    private Gson gson = new Gson();

    @Test
    public void contextLoads() {
        //Staff staff = staffService.findByStaffName(staff_name);
        //if (null == staff) {
        //    throw new UsernameNotFoundException("用户名不存在");
        //}
        //System.out.println(staff.toString());
        //List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //for (Role role : staff.getRoles()) {
        //authorities.add(new SimpleGrantedAuthority(role.getRole()));
        //System.out.println(role.getRole() + "," + role.getRole_name());
        //}
        //Staff staff = staffRepository.findByStaffName(staff_name);
        //System.out.println(staff.toString());
        //List<Vocation> vocationList = vocationService.getAllVocationByApplicant("李攀");
        //System.out.println(vocationList);
        //vocationService.vocationReadState(Integer.valueOf(1));
        vocationService.deleteVocation(Integer.valueOf(1));
    }

    @Test
    public void index() {
        //String role = "ROLE_ADMIN";
        //List<String> list = staffRepository.findByRole(role);
        //Random random = new Random();
        //int n = random.nextInt(list.size());
        //System.out.println(list.get(n));
        //Calendar calendar = Calendar.getInstance();
        //int h = calendar.get(Calendar.HOUR_OF_DAY);
        //int mi=calendar.get(Calendar.MINUTE);
        //int s=calendar.get(Calendar.SECOND);
        //String time = calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);
        //System.out.println(time);
        //Calendar calendar = Calendar.getInstance();
        //String time = calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);
        //String applicant_name = request.getParameter("applicant");
        //String leave_days = request.getParameter("days");
        //String leave_date = request.getParameter("date");
        //String leave_reason = request.getParameter("why");
        //List<String> roleList = staffRepository.findByRole("ROLE_ADMIN");
        //Random random = new Random();
        //Vocation vocation = new Vocation(applicant_name,roleList.get(random.nextInt(roleList.size())),new Timestamp(System.currentTimeMillis()),time,leave_days,leave_date,leave_reason);
        //vocationService.addLeave(vocation);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(new Timestamp(System.currentTimeMillis())));
    }

}
