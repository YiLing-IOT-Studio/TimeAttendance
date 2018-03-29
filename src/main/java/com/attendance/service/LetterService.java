package com.attendance.service;

import com.attendance.entity.Letter;
import com.attendance.mapper.LetterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FantasticPan on 2018/3/26.
 */
@Service
public class LetterService {

    @Autowired
    private LetterMapper letterMapper;

    public Long getLetterNumByName(String accept_name) {
        return letterMapper.getLetterNumByName(accept_name);
    }

    public List<Letter> getAllLetterByAccepter(String accept_name) {
        return letterMapper.getAllLetterByAccepter(accept_name);
    }

    public void addLetter(Letter letter) {
        letterMapper.addLetter(letter);
    }

    public void letterState(Integer id) {
        letterMapper.letterState(id);
    }
 }
