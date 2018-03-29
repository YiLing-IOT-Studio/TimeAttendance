package com.attendance.test;

import com.attendance.entity.Letter;
import com.attendance.service.LetterService;

import java.sql.Timestamp;

/**
 * Created by FantasticPan on 2018/3/28.
 */
public class Test {

    public static void main(String[] args) {

        Letter letter = new Letter("fewf","fewf",new Timestamp(System.currentTimeMillis()),"deqrd","deqd","fewf");
        LetterService letterService = new LetterService();
        letterService.addLetter(letter);
    }
}
