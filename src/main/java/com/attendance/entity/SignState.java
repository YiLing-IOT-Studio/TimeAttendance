package com.attendance.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by FantasticPan on 2018/3/27.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "sign_state")
public class SignState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer state;
}
