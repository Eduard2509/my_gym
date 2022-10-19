package com.alevel.gym.repository;

import com.alevel.gym.model.Coach;
import com.alevel.gym.model.NamesSubscription;
import com.alevel.gym.model.Subscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, String> {

    Subscription findSubscriptionByNamesSubscription(NamesSubscription namesSubscription);
}
