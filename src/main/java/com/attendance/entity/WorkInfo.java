package com.attendance.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by FantasticPan on 2018/3/31.
 */
@Data
@ToString
@Entity
@Table(name = "work_info")
public class WorkInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String staffName;
    private String staffId;
    private Integer workDay;
    private Integer restDay;
    private Date workDate;
    private String leaveDate;

    public WorkInfo() {
    }

    public WorkInfo(String staffName, String staffId, Integer workDay, Integer restDay, Date workDate, String leaveDate) {
        this.staffName = staffName;
        this.staffId = staffId;
        this.workDay = workDay;
        this.restDay = restDay;
        this.workDate = workDate;
        this.leaveDate = leaveDate;
    }

    public WorkInfo(String staffName, String staffId, Integer workDay, Integer restDay, Date workDate) {
        this.staffName = staffName;
        this.staffId = staffId;
        this.workDay = workDay;
        this.restDay = restDay;
        this.workDate = workDate;
    }
}
