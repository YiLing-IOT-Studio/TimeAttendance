package com.attendance;

import com.attendance.entity.Letter;
import com.attendance.entity.Staff;
import com.attendance.repository.StaffRepository;
import com.attendance.service.LetterService;
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
    private LetterService letterService;

    private Gson gson = new Gson();

    @Test
    public void contextLoads() {
        String staff_name = "李攀";
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
        Staff staff = staffRepository.findByStaffName(staff_name);
        System.out.println(staff.toString());
    }

    @Test
    public void index() {
        String name = "李攀";
        ////System.out.println(letterService.getLetterNumByName(name));
        //Long letter_num = letterService.getLetterNumByName(name);
        List<Letter> letters = letterService.getAllLetterByAccepter(name);
        ////System.out.println(hh);
        System.out.println(gson.toJson(letters));
        //System.out.println(letters);
        //System.out.println(letter_num);
        //Letter letter = new Letter("fewf","fewf",new Timestamp(System.currentTimeMillis()),"deqrd","deqd","fewf");
        //letterService.addLetter(letter);
    }

}
