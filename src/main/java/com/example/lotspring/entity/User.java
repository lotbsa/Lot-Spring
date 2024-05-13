package com.example.lotspring.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Builder
public class User implements Serializable {

    private Integer id;
    @NotBlank
    private String sex;
    @Range(min = 1, max = 100)
    private Integer age;
    private String name;
    @Email
    private String email;
    private String telephone;
    private String address;
    private Timestamp createTime;

}
