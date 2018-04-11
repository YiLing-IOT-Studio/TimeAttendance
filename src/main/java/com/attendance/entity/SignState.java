package com.attendance.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by FantasticPan on 2018/3/27.
 */
@Data
@Entity
@Table(name = "sign_state")
public class SignState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(columnDefinition="VARCHAR(255) default 'display'")
    private String markOne;
    @Column(columnDefinition="VARCHAR(255) default 'hidden'")
    private String markTwo;
}
