package com.attendance.controller;

import com.attendance.entity.Staff;
import com.attendance.repository.StaffRepository;
import com.attendance.service.SignStateService;
import com.attendance.service.StaffRoleService;
import com.attendance.service.WorkInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by FantasticPan on 2018/4/1.
 */
@RestController
public class StaffController {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private StaffRoleService staffService;
    @Autowired
    private StaffRoleService staffRoleService;
    @Autowired
    private WorkInfoService workInfoService;
    @Autowired
    private SignStateService signStateService;

    private Integer flag = 0;

    @GetMapping("/getStaffByName")
    public Staff getStaffByName(String name) {
        return staffRepository.findStaffByName(name);
    }

    @GetMapping("/getAllStaff")
    public List<Staff> getAllStaff() {
        return staffRepository.findAllStaff("ROLE_USER");
    }

    @PostMapping("/addStaff")
    public void addStaff(Staff staff) {
        staffRoleService.addStaff(staff);
        Integer staff_id = staff.getId();
        Integer rol_id = staffRoleService.getRoleId("ROLE_USER");
        staffRoleService.addStaffWithRole(staff_id,rol_id);
        signStateService.addSignState(staff.getStaffName());
    }

    @PostMapping("/delStaff")
    public void delStaff(HttpServletRequest request) {
        String staffId = request.getParameter("staffId");
        String staffName = request.getParameter("staffName");
        Integer staff_id = Integer.valueOf(staffId);
        staffRoleService.delStaffWithRole(staff_id);
        workInfoService.delAllWorkInfoByName(staffName);
        signStateService.delSignState(staffName);
    }

    /**
     * 基于用户标识的头像上传
     * @return*/
    @ResponseBody
    @RequestMapping(value = "/uploadImg")
    public String uploadImg(HttpServletRequest request, @RequestParam("croppedImage") MultipartFile multipartFile) throws IOException {

        //主体名，即登录用户名
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Staff staff = new Staff();
        String fileName = multipartFile.getOriginalFilename();
        // 新的图片文件名 = 获取时间戳+"."图片扩展名
        String newFileName = username + ".png";
        if (!multipartFile.isEmpty()) {
            if (multipartFile.getContentType().contains("image")) {
                try {
                    // 获取图片的文件名
                    System.out.println("filename:"+fileName);
                    // 文件路径
                    String filePath = request.getSession().getServletContext().getRealPath("/")+"upload/";
                    File dest = new File(filePath + newFileName);
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();
                    }
                    // 上传到指定目录
                    multipartFile.transferTo(dest);

                    staff.setStaffName(username);
                    staff.setHeadPortrait(newFileName);
                    staffService.uploadImgUrl(staff);
                } catch (IOException e) {
                    e.printStackTrace();
                    return "上传失败";
                }
            }
        }
        flag = 1;
        return "http://119.29.233.28:7070/upload/" + staff.getHeadPortrait();
    }

    /**
     * 回显头像
     * @return*/
    @ResponseBody
    @RequestMapping(value = "/viewImg")
    public String viewImg(HttpServletRequest request) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); //主体名，即登录用户名
        System.out.println("username is:" + username);

        Staff staff = staffService.viewImg(username);
        if(flag == 0){
            return "/img/initStaff.png";
        }else {
            return "http://119.29.233.28:7070/upload/" + staff.getHeadPortrait();
        }
    }
}
