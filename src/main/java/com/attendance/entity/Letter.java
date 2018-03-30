package com.attendance.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by FantasticPan on 2018/3/26.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "letter")
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String sender;
    private String accepter;
    private Timestamp date;
    private String time;
    private String main_content;
    private String all_content;
    @Column(columnDefinition="VARCHAR(255) default 'unRead'")
    private String status;

    public Letter() {
    }

    public Letter(String sender, String accepter, Timestamp date, String time, String main_content, String all_content) {
        this.sender = sender;
        this.accepter = accepter;
        this.date = date;
        this.time = time;
        this.main_content = main_content;
        this.all_content = all_content;
    }
}
