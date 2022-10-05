package com.alevel.gym.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private NamesSubscription namesSubscription;
    private double price;
    @OneToOne
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;

    public Subscription(NamesSubscription namesSubscription, double price) {
        this.namesSubscription = namesSubscription;
        this.price = price;
    }
}
