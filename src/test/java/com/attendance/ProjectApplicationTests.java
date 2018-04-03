package com.attendance;

import com.attendance.repository.StaffRepository;
import com.attendance.service.VocationService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectApplicationTests {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private VocationService vocationService;

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
        calendar.set(year2,month2,0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = year+"-"+month+"-"+"01";
        String date2 = year+"-"+month+"-"+calendar.get(Calendar.DAY_OF_MONTH);
        Date start = sdf.parse(date1);
        Date end = sdf.parse(date2);
        List<String> vocationList = vocationService.getVocationNameByDate(start,end);
        HashSet hashSet = new HashSet(vocationList);
        vocationList.clear();
        vocationList.addAll(hashSet);
        System.out.println(vocationList);
    }

}
