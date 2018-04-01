package com.attendance.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by FantasticPan on 2018/3/31.
 */
@Getter
@Setter
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
}
