package com.alevel.gym.repository;

import com.alevel.gym.model.Coach;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoachRepository extends CrudRepository<Coach, String> {

    Optional<Coach> findByName(String name);
}
