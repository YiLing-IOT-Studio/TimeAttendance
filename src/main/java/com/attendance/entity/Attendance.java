package com.attendance.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

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
    private Timestamp inTime;
    private Timestamp outTime;
    private String attendance;

    public Attendance() {
    }
}
