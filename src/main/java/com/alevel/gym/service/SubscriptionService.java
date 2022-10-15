package com.alevel.gym.service;

import com.alevel.gym.dto.SubscriptionDTO;
import com.alevel.gym.mapper.SubscriptionMapper;
import com.alevel.gym.model.Coach;
import com.alevel.gym.model.NamesSubscription;
import com.alevel.gym.model.StatusPeople;
import com.alevel.gym.model.Subscription;
import com.alevel.gym.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SubscriptionService {

    SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Iterable<Subscription> getAll() {
        return subscriptionRepository.findAll();
    }

    public Subscription save(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public Subscription update(Subscription subscription){
        return subscriptionRepository.save(subscription);
    }

    public Subscription findById(String id) {
        return subscriptionRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }


    public void deleteById(String id){
        if (subscriptionRepository.existsById(id)) {
            subscriptionRepository.deleteById(id);
        }
    }

}
