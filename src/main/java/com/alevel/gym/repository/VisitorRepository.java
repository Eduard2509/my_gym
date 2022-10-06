package com.alevel.gym.repository;

import com.alevel.gym.model.Visitor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends CrudRepository<Visitor, String> {
}
