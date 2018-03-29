package com.attendance.mapper;

import com.attendance.entity.Letter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FantasticPan on 2018/3/26.
 */
@Service
public interface LetterMapper {

    Long getLetterNumByName(String accept_name);
    List<Letter> getAllLetterByAccepter(String accept_name);
    void addLetter(Letter letter);
    void letterState(Integer id);
    void deleteLetter(Integer id);
}
