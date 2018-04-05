package com.attendance.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by FantasticPan on 2018/3/26.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "vocation")
public class Vocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String applicant;
    private String admin;
    private Date date;
    private String time;
    private String leave_days;
    private String leave_date;
    private String leave_reason;
    private String all_content;
    @Column(columnDefinition="VARCHAR(255) default 'unRead'")
    private String read_state;
    @Column(columnDefinition="VARCHAR(255) default 'unHandle'")
    private String handle_state;

    public Vocation() {
    }

    public Vocation(String applicant, String admin, Date date, String time, String leave_days, String leave_date, String leave_reason) {
        this.applicant = applicant;
        this.admin = admin;
        this.date = date;
        this.time = time;
        this.leave_days = leave_days;
        this.leave_date = leave_date;
        this.leave_reason = leave_reason;
    }
}
