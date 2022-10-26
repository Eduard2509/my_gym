package com.alevel.gym.repository;

import com.alevel.gym.model.Coach;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository extends CrudRepository<Coach, String> {

    Coach findByName(String name);

    @Query(value = "select * from coach where name = :value or surname = :value", nativeQuery = true)
    List<Coach> findByNameOrSurname(@Param("value") String value);
}
