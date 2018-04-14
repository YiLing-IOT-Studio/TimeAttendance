package com.attendance.entity;

import lombok.Data;

/**
 * Created by FantasticPan on 2018/4/13.
 */
@Data
public class AttendanceInfo {

    private Integer id;
    private String inTime;
    private String outTime;
    private String totalMilli;

    public AttendanceInfo() {
    }

    public AttendanceInfo(Integer id, String inTime, String outTime, String totalMilli) {
        this.id = id;
        this.inTime = inTime;
        this.outTime = outTime;
        this.totalMilli = totalMilli;
    }
}
