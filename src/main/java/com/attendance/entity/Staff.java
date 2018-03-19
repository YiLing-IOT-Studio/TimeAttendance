package com.attendance.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by FantasticPan on 2018/3/18.
 */
@Getter
@Setter
@ToString
public class Staff {

    private Integer id;
    private String staffName;
    private String comeTime;
    private String leaveTime;

    public Staff() {
    }

    public Staff(String staffName, String comeTime, String leaveTime) {
        this.staffName = staffName;
        this.comeTime = comeTime;
        this.leaveTime = leaveTime;
    }
}
