package com.alevel.gym.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
    private StatusPeople statusPeople;
    private String header;
    private String body;

    @OneToMany(mappedBy = "coach",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private List<Visitor> visitors;

    public Coach(String id, String name, String surname, int age, Sex sex) {
        super(id, name, surname, sex);
        this.age = age;
        this.statusPeople = StatusPeople.COACH;
    }

}
