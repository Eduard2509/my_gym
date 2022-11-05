package com.alevel.gym.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class LockedRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private int value;
    private String imageURL;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Enumerated(EnumType.STRING)
    private Condition condition;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;

    public LockedRoom(String id, int value, String imageURL, Sex sex, Condition condition, Visitor visitor) {
        this.id = id;
        this.value = value;
        this.imageURL = imageURL;
        this.sex = sex;
        this.condition = condition;
        this.visitor = visitor;
    }
}
