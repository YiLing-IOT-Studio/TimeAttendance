package com.attendance.controller;

import com.attendance.entity.Letter;
import com.attendance.service.LetterService;
import com.attendance.util.SecurityUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by FantasticPan on 2018/3/27.
 */
@Controller
public class LetterController {

    @Autowired
    private LetterService letterService;

    private Gson gson = new Gson();

    @GetMapping("/letters")
    @ResponseBody
    public String letterList(HttpServletResponse response) {
        String accepter_name = SecurityUtil.getCurrentUsername();
        List<Letter> letters = letterService.getAllLetterByAccepter(accepter_name);
        return gson.toJson(letters);
    }

    @PostMapping("/read_state")
    @ResponseBody
    public void letterState(HttpServletRequest request) {
        String[] ids = request.getParameterValues("markRead");
        if (ids.length != 0) {
            for (int i = 0;i<ids.length;i++) {
                letterService.letterState(Integer.valueOf(ids[i]));
            }
        }
    }

    @PostMapping("/delete_letter")
    @ResponseBody
    public void deleteLetter(HttpServletRequest request) {
        String[] ids = request.getParameterValues("markRead");
        if (ids.length != 0) {
            for (int i = 0;i<ids.length;i++) {
                letterService.letterState(Integer.valueOf(ids[i]));
            }
        }
    }
}