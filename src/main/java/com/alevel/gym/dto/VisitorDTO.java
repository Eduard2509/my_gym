package com.alevel.gym.dto;

import com.alevel.gym.model.Sex;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class VisitorDTO {
    private String name;
    private String surname;
    private int age;
    @Email
    private String email;
    private String password;
    private Sex sex;
}
