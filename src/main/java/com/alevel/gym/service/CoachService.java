package com.alevel.gym.service;

import com.alevel.gym.dto.CoachDTO;
import com.alevel.gym.mapper.CoachMapper;
import com.alevel.gym.model.Coach;
import com.alevel.gym.model.StatusPeople;
import com.alevel.gym.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoachService {

    CoachRepository coachRepository;

    @Autowired
    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    public Coach findByName(String name) {
        return coachRepository.findByName(name);
    }

    public Iterable<Coach> getAll() {
        return coachRepository.findAll();
    }

    public Coach save(CoachDTO coachDTO) {
        Coach coach = CoachMapper.mapFromDTO(coachDTO);
        coach.setStatusPeople(StatusPeople.COACH);
        return coachRepository.save(coach);
    }

    public Coach findById(String id) {
        return coachRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Coach update(Coach coach) {
        coach.setStatusPeople(StatusPeople.COACH);
        return coachRepository.save(coach);
    }

    public void deleteById(String id) {
        if (coachRepository.existsById(id)) {
            coachRepository.deleteById(id);
        }
    }

}
