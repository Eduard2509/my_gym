package com.alevel.gym.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public abstract class People {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    protected String id;
    protected String name;
    protected String surname;
    protected Sex sex;

    protected People(String id, String name, String surname, Sex sex) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
    }

}
