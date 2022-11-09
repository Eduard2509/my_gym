package com.alevel.gym.repository;

import com.alevel.gym.model.Visitor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, String> {

    @Query(value = "select visitor.id, visitor.name, " +
            "visitor.surname, visitor.age, " +
            "visitor.email, visitor.password, visitor.sex, visitor.status_people, visitor.locked_id, " +
            "visitor.coach_id, visitor.subscription_id " +
            "from visitor " +
            "left join coach on visitor.coach_id = coach.id " +
            "left join subscription on visitor.subscription_id = subscription.id " +
            "where visitor.status_people = :statusPeople", nativeQuery = true)
    List<Visitor> findAllByStatusPeople(@Param("statusPeople")String statusPeople);

    Visitor findByEmail(String email);
}
