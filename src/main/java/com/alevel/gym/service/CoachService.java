package com.alevel.gym.service;

import com.alevel.gym.dto.CoachDTO;
import com.alevel.gym.mapper.CoachMapper;
import com.alevel.gym.model.Coach;
import com.alevel.gym.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CoachService {

    CoachRepository coachRepository;

    @Autowired
    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    public Iterable<Coach> getAll() {
        return coachRepository.findAll();
    }

    public CoachDTO findById(String id) {
        Coach byId = coachRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return CoachMapper.mapToDTO(byId);
    }

    public Optional<Coach> findByName(String name) {
        return coachRepository.findByName(name);
    }

    public CoachDTO update(String id, CoachDTO coachDTO){
        final Coach findCoach =
                coachRepository.findById(id).orElseThrow(IllegalAccessError::new);
        findCoach.setAge(coachDTO.getAge());
        findCoach.setName(coachDTO.getName());
        findCoach.setSurname(coachDTO.getSurname());
        final Coach coachUpdated = coachRepository.save(findCoach);
        return CoachMapper.mapToDTO(coachUpdated);
    }

    public void deleteById(String id){
        if (coachRepository.existsById(id)) {
            coachRepository.deleteById(id);
        }
    }

    public String createDefaultCoach() {
        final Coach coach = new Coach();
        coach.setId(UUID.randomUUID().toString());
        coach.setName("Default name");
        coach.setSurname("Default surname");
        coach.setAge(18);
        return coachRepository.save(coach).getId();
    }

}
