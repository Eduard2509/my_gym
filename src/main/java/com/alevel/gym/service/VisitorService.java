package com.alevel.gym.service;

import com.alevel.gym.dto.VisitorDTO;
import com.alevel.gym.mapper.VisitorMapper;
import com.alevel.gym.model.StatusPeople;
import com.alevel.gym.model.Visitor;
import com.alevel.gym.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VisitorService {

    VisitorRepository visitorRepository;

    @Autowired
    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    public void save(VisitorDTO visitorDTO) {
        Visitor visitor = VisitorMapper.mapFromDTO(visitorDTO);
        visitor.setStatusPeople(StatusPeople.VISITOR);
        visitorRepository.save(visitor);
    }

    public Iterable<Visitor> getAll(){
        return visitorRepository.findAll();
    }

    public void deleteById(String id) {
        visitorRepository.deleteById(id);
    }

    public Visitor update(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    public Visitor findById(String id) {
        return visitorRepository.findById(id).orElseThrow(IllegalAccessError::new);
    }
}
