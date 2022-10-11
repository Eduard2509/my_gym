package com.alevel.gym.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private StatusPeople statusPeople;
    @Email
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;


    public Visitor(String id, String name, String surname, int age, String email, String password, Sex sex) {
        super(id, name, surname, sex);
        this.age = age;
        this.statusPeople = StatusPeople.VISITOR;
        this.email = email;
        this.password = password;
    }
}
