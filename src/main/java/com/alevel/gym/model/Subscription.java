package com.alevel.gym.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.util.Set;

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

    @Enumerated(EnumType.STRING)
    private NamesSubscription namesSubscription;

    private int price;
    private String title;
    private String description;
    @URL
    private String imageURL;

    @OneToMany(mappedBy = "subscription",
            fetch = FetchType.EAGER)
    private Set<Visitor> visitors;

    public Subscription(NamesSubscription namesSubscription, int price, String title, String description, String imageURL) {
        this.namesSubscription = namesSubscription;
        this.price = price;
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
    }
}
