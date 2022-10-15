package com.alevel.gym.repository;

import com.alevel.gym.model.StatusPeople;
import com.alevel.gym.model.Visitor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends CrudRepository<Visitor, String> {

    @Query(value = "select * from Visitor where status_people = :statusPeople", nativeQuery = true)
    Iterable<Visitor> findAllByStatusPeople(@Param("statusPeople")String statusPeople);

    Visitor findByEmail(String email);
}
