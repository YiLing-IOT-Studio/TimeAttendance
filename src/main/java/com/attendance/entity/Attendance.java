package com.attendance.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by FantasticPan on 2018/3/18.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String staffName;
    private String inTime;
    private String outTime;
    private String attendance;

    public Attendance() {
    }

    public Attendance(String staffName, String inTime, String outTime, String attendance) {
        this.staffName = staffName;
        this.inTime = inTime;
        this.outTime = outTime;
        this.attendance = attendance;
    }
}
