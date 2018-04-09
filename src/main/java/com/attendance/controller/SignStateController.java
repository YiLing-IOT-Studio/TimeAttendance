package com.attendance.controller;

import com.attendance.entity.SignState;
import com.attendance.service.SignStateService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by FantasticPan on 2018/3/29.
 */
@Controller
public class SignStateController {

    @Autowired
    private SignStateService signStateService;
    @Autowired
    private Gson gson;

    @RequestMapping("/allSignState")
    public String getAllSignState() {
        List<SignState> signStateList = signStateService.getAllSignState();
        return gson.toJson(signStateList);
    }
}
