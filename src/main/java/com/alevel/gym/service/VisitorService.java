package com.alevel.gym.service;

import com.alevel.gym.dto.VisitorDTO;
import com.alevel.gym.model.Visitor;
import com.alevel.gym.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitorService {

    VisitorRepository visitorRepository;

    @Autowired
    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    public void save(Visitor visitor) {
        visitorRepository.save(visitor);
    }
}
