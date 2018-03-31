package com.attendance.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by FantasticPan on 2018/3/31.
 */
@Getter
@Setter
@ToString
public class JsonTest {

    private String staffName;
    private String staffId;
    private Integer workDay;
    private Integer restDay;

    public JsonTest() {
    }

    public JsonTest(String staffName, String staffId, Integer workDay, Integer restDay) {
        this.staffName = staffName;
        this.staffId = staffId;
        this.workDay = workDay;
        this.restDay = restDay;
    }
}
