package com.alevel.gym.service;

import com.alevel.gym.dto.SubscriptionDTO;
import com.alevel.gym.mapper.SubscriptionMapper;
import com.alevel.gym.model.NamesSubscription;
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

    public SubscriptionDTO findById(String id) {
        Subscription byId = subscriptionRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return SubscriptionMapper.mapToDTO(byId);
    }

    public Optional<Subscription> findByName(String name) {
        return subscriptionRepository.findSubscriptionByNamesSubscription(name);
    }

    public SubscriptionDTO update(String id, SubscriptionDTO subscriptionDTO){
        final Subscription subscription =
                subscriptionRepository.findById(id).orElseThrow(IllegalAccessError::new);
        subscription.setNamesSubscription(subscriptionDTO.getNamesSubscription());
        subscription.setPrice(subscriptionDTO.getPrice());
        final Subscription subscriptionUpdated = subscriptionRepository.save(subscription);
        return SubscriptionMapper.mapToDTO(subscriptionUpdated);
    }

    public void deleteById(String id){
        if (subscriptionRepository.existsById(id)) {
            subscriptionRepository.deleteById(id);
        }
    }

    public String createDefaultSubscription() {
        final Subscription subscription = new Subscription();
        subscription.setId(UUID.randomUUID().toString());
        subscription.setNamesSubscription(NamesSubscription.OPTIMAL);
        subscription.setPrice(900);
        return subscriptionRepository.save(subscription).getId();
    }

}
