package com.alevel.gym.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class Visitor extends People {

    @Min(12)
    @Max(65)
    private int age;
    private StatusPeople statusPeople;
    @Email
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;


    public Visitor(String id, String name, String surname, int age, String email, String password, Sex sex) {
        super(id, name, surname, sex);
        this.age = age;
        this.statusPeople = StatusPeople.VISITOR;
        this.email = email;
        this.password = password;
    }
}
