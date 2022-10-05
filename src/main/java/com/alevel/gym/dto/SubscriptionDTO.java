package com.alevel.gym.dto;

import com.alevel.gym.model.NamesSubscription;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionDTO {
    private NamesSubscription namesSubscription;
    private double price;
}
