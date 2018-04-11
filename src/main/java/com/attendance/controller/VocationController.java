package com.attendance.controller;

import com.attendance.entity.Vocation;
import com.attendance.repository.StaffRepository;
import com.attendance.service.VocationService;
import com.attendance.util.SecurityUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by FantasticPan on 2018/3/26.
 */
@Controller
public class VocationController {

    @Autowired
    private VocationService vocationService;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private Gson gson;

    @PostMapping("/vocation")
    public String leave(HttpServletRequest request) {
        String applicant_name = request.getParameter("applicant");
        String leave_days = request.getParameter("days");
        String leave_date = request.getParameter("date");
        String leave_reason = request.getParameter("why");
        List<String> roleList = staffRepository.findByRole("ROLE_ADMIN");
        Random random = new Random();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Vocation vocation =
                new Vocation(applicant_name, roleList.get(random.nextInt(roleList.size())),
                        new Date(System.currentTimeMillis()), sdf.format(new Date()),
                        leave_days, leave_date, leave_reason);
        vocationService.addVocation(vocation);
        return "redirect:/personal_center";
    }

    @GetMapping("/vocations")
    @ResponseBody
    public String vocationList() {
        String applicant_name = SecurityUtil.getCurrentUsername();
        List<Vocation> vocationList = vocationService.getAllVocationByApplicant(applicant_name);
        return gson.toJson(vocationList);
    }

    @PostMapping("/vocations_admin")
    @ResponseBody
    public String vocationListAdmin() {
        String admin = SecurityUtil.getCurrentUsername();
        List<Vocation> vocationList = vocationService.getAllVocationByAdmin(admin);
        return gson.toJson(vocationList);
    }

    @ResponseBody
    @PostMapping("/read_state")
    public void vocationState(HttpServletRequest request) {
        String[] ids = request.getParameterValues("markRead");
        if (ids.length != 0) {
            for (String id : ids) {
                vocationService.vocationReadState(Integer.valueOf(id));
            }
        }
    }

    @ResponseBody
    @PostMapping("/delete_vocation")
    public void deleteVocation(HttpServletRequest request) {
        String[] ids = request.getParameterValues("markRead");
        if (ids.length != 0) {
            for (String id : ids) {
                vocationService.deleteVocation(Integer.valueOf(id));
            }
        }
    }

    @ResponseBody
    @PostMapping("/handle_vocation")
    public String handleVocation(HttpServletRequest request) {
        String id = request.getParameter("id");
        Integer id1 = Integer.valueOf(id);
        String result = request.getParameter("result");
        vocationService.handleVocation(id1, result);
        return gson.toJson(vocationService.getVocationByContent(id1));
    }
}
