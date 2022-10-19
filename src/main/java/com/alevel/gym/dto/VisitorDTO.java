package com.alevel.gym.dto;

import com.alevel.gym.model.Coach;
import com.alevel.gym.model.Sex;
import com.alevel.gym.model.Subscription;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Getter
@Setter
public class VisitorDTO {
    @Id
    private String id;
    private String name;
    private String surname;
    private int age;
    @Email
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private Subscription subscription;
    private Coach coach;
}
