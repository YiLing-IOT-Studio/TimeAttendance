package com.attendance.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

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
    private Date inTime;
    private Date outTime;
    private Long totalMill;

    // TODO: 2018/4/12 inTime时间代码优化
    public Attendance(String staffName, Date inTime) {
        this.staffName = staffName;
        this.inTime = inTime;
    }

    public Attendance(String staffName, Date inTime, Long totalMill) {
        this.staffName = staffName;
        this.inTime = inTime;
        this.totalMill = totalMill;
    }
}
