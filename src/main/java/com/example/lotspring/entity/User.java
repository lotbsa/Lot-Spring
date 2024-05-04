package com.example.lotspring.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Builder
public class User implements Serializable {

    private Integer id;
    private String sex;
    private Integer age;
    private String name;
    private String email;
    private String telephone;
    private String address;
    private Timestamp createTime;

}
