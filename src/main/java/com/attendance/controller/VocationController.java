package com.attendance.controller;

import com.attendance.entity.Vocation;
import com.attendance.repository.StaffRepository;
import com.attendance.service.VocationService;
import com.attendance.util.SecurityUtil;
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

    @PostMapping("/vocation")
    public String leave(Vocation vocation_local) {
        List<String> roleList = staffRepository.findByRole("ROLE_ADMIN");
        Random random = new Random();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Vocation vocation_complete =
                new Vocation(vocation_local.getApplicant(), roleList.get(random.nextInt(roleList.size())),
                        new Date(System.currentTimeMillis()),
                        sdf.format(new Date()), vocation_local.getLeave_days(),
                        vocation_local.getLeave_date(), vocation_local.getLeave_reason());
        vocationService.addVocation(vocation_complete);
        return "redirect:/personal_center";
    }

    @ResponseBody
    @GetMapping("/vocations")
    public List<Vocation> vocationList() {
        String applicant_name = SecurityUtil.getCurrentUsername();
        return vocationService.getAllVocationByApplicant(applicant_name);
    }

    @ResponseBody
    @PostMapping("/vocations_admin")
    public List<Vocation> vocationListAdmin() {
        String admin = SecurityUtil.getCurrentUsername();
        return vocationService.getAllVocationByAdmin(admin);
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
    public void handleVocation(HttpServletRequest request) {
        String id = request.getParameter("id");
        Integer id1 = Integer.valueOf(id);
        String result = request.getParameter("result");
        vocationService.handleVocation(id1, result);
    }
}
