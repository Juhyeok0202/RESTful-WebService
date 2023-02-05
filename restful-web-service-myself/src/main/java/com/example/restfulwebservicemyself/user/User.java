package com.example.restfulwebservicemyself.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * 사용자 도메인 클래스
 */
@Data
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private Date joinDate;
}
