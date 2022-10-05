package com.alevel.gym.mapper;

import com.alevel.gym.dto.CoachDTO;
import com.alevel.gym.model.Coach;

public final class CoachMapper {

    private CoachMapper() {}

    public static CoachDTO mapToDTO(Coach coach){
        CoachDTO coachDTO = new CoachDTO();
        coachDTO.setName(coach.getName());
        coachDTO.setSurname(coach.getSurname());
        coachDTO.setAge(coach.getAge());
        return coachDTO;
    }

    public static Coach mapFromDTO(CoachDTO coachDTO){
        final Coach coach = new Coach();
        coach.setName(coachDTO.getName());
        coach.setSurname(coachDTO.getSurname());
        coach.setAge(coach.getAge());
        return coach;
    }
}
