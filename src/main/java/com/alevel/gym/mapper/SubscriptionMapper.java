package com.alevel.gym.mapper;

import com.alevel.gym.dto.SubscriptionDTO;
import com.alevel.gym.model.Subscription;

public class SubscriptionMapper {

    private  SubscriptionMapper(){}

    public static SubscriptionDTO mapToDTO(Subscription subscription){
        final SubscriptionDTO dto = new SubscriptionDTO();
        dto.setNamesSubscription(subscription.getNamesSubscription());
        dto.setPrice(subscription.getPrice());
        dto.setDescription(subscription.getDescription());
        dto.setTitle(subscription.getTitle());
        dto.setImageURL(subscription.getImageURL());
        return dto;
    }

    public static Subscription mapFromDTO(SubscriptionDTO subscriptionDTO) {
        final Subscription subscription = new Subscription();
        subscription.setNamesSubscription(subscriptionDTO.getNamesSubscription());
        subscription.setPrice(subscriptionDTO.getPrice());
        subscription.setTitle(subscriptionDTO.getTitle());
        subscription.setDescription(subscription.getDescription());
        subscription.setImageURL(subscription.getImageURL());
        return subscription;
    }
}
