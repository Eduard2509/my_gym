package com.alevel.gym.dto;

import com.alevel.gym.model.NamesSubscription;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Getter
@Setter
public class SubscriptionDTO {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private NamesSubscription namesSubscription;
    private int price;
    private String description;
    private String title;
    private String imageURL;
}
