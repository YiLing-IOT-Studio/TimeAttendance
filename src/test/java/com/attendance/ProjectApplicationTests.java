package com.attendance;

import com.attendance.entity.Staff;
import com.attendance.entity.WorkInfo;
import com.attendance.repository.StaffRepository;
import com.attendance.service.StaffRoleService;
import com.attendance.service.VocationService;
import com.attendance.service.WorkInfoService;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    public void index() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        String month = "4";
        String year = "2018";
        Integer month2 = Integer.valueOf(month);
        Integer year2 = Integer.valueOf(year);
        calendar.set(year2, month2, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = year + "-" + month + "-" + "01";
        String date2 = year + "-" + month + "-" + calendar.get(Calendar.DAY_OF_MONTH);
        Date start = sdf.parse(date1);
        Date end = sdf.parse(date2);
        List<Staff> staffList = staffRepository.findAllStaff("ROLE_USER");
        for (Staff staff : staffList) {
            Integer rest_day = vocationService.getWorkDayByDate(start, end, staff.getStaffName());
            if (rest_day == null) {
                rest_day = 0;
            }
            List<String> leaveDateList = vocationService.getLeaveDateByName(staff.getStaffName());
            if (leaveDateList.size() == 0) {
                String leaveDate = "";
            }
            String leaveDate = StringUtils.join(leaveDateList, ",");
            String staffNum = String.format("%0" + 3 + "d", staff.getId());
            WorkInfo workInfo = new WorkInfo(staff.getStaffName(), staffNum,
                    calendar.get(Calendar.DAY_OF_MONTH) - rest_day, rest_day, start, leaveDate);
            workInfoService.addOrUpdate(workInfo);
        }
        List<WorkInfo> workInfoList = workInfoService.getAllWorkInfo(start);
        System.out.println(workInfoList);
    }

}
