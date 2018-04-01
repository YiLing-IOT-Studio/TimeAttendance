package com.attendance.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Created by FantasticPan on 2018/4/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VocationControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void vocationListAdmin() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/vocations_admin"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}