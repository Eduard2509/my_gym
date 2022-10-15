package com.alevel.gym.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class Coach extends People {

    @Min(18)
    @Max(45)
    private int age;
    @Enumerated(EnumType.STRING)
    private StatusPeople statusPeople;
    private String description;
    @URL
    private String imageURL;

    @OneToMany(mappedBy = "coach",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER)
    private List<Visitor> visitors;

    public Coach(String id, String name, String surname, int age,  Sex sex, String description, String imageURL) {
        super(id, name, surname, sex);
        this.description = description;
        this.age = age;
        this.imageURL = imageURL;
        this.statusPeople = StatusPeople.COACH;
    }

}
