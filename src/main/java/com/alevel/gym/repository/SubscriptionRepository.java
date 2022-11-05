package com.alevel.gym.repository;

import com.alevel.gym.model.NamesSubscription;
import com.alevel.gym.model.Subscription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, String> {

    Subscription findSubscriptionByNamesSubscription(NamesSubscription namesSubscription);

    @Query(value = "select * from Subscription order by price asc ", nativeQuery = true)
    public List<Subscription> findAllAndOrderByPrice();
}
