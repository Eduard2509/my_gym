package com.alevel.gym.repository;

import com.alevel.gym.model.StatusPeople;
import com.alevel.gym.model.Visitor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, String> {

    @Query(value = "select * from Visitor where status_people = :statusPeople", nativeQuery = true)
    Iterable<Visitor> findAllByStatusPeople(@Param("statusPeople")String statusPeople);

    @Query(value = "select * from Visitor where status_people = :statusPeople", nativeQuery = true)
    Page<Visitor> findAllByStatusPeople(@Param("statusPeople")String statusPeople, Pageable pageable);

    Visitor findByEmail(String email);
}
