package com.attendance.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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
    private String leave_days;
    private String leave_date;
    private String leave_reason;

    public Vocation() {
    }

    public Vocation(String applicant, String leave_days, String leave_date, String leave_reason) {
        this.applicant = applicant;
        this.leave_days = leave_days;
        this.leave_date = leave_date;
        this.leave_reason = leave_reason;
    }
}
