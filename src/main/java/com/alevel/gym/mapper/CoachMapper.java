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
        coachDTO.setSex(coach.getSex());
        coachDTO.setDescription(coach.getDescription());
        coachDTO.setImageURL(coach.getImageURL());
        return coachDTO;
    }

    public static Coach mapFromDTO(CoachDTO coachDTO){
        final Coach coach = new Coach();
        coach.setName(coachDTO.getName());
        coach.setSurname(coachDTO.getSurname());
        coach.setAge(coachDTO.getAge());
        coach.setSex(coachDTO.getSex());
        coach.setDescription(coachDTO.getDescription());
        coach.setImageURL(coachDTO.getImageURL());
        return coach;
    }
}
