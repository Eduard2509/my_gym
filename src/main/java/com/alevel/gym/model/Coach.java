package com.alevel.gym.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class Coach extends People {

    @Min(value = 18, message = "Too old to coach")
    @Max(value = 45, message = "Too young to coach")
    private int age;
    @Enumerated(EnumType.STRING)
    private StatusPeople statusPeople;
    private String description;
    @URL
    private String imageURL;

    @OneToMany(mappedBy = "coach",
            cascade = {CascadeType.REMOVE, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    private Set<Visitor> visitors;

    public Coach(String id, String name, String surname, int age, Sex sex, String description, String imageURL) {
        super(id, name, surname, sex);
        this.description = description;
        this.age = age;
        this.imageURL = imageURL;
        this.statusPeople = StatusPeople.COACH;
    }

}
