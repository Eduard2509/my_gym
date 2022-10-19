package com.alevel.gym.mapper;

import com.alevel.gym.dto.CoachDTO;
import com.alevel.gym.dto.VisitorDTO;
import com.alevel.gym.model.Coach;
import com.alevel.gym.model.Visitor;

public class VisitorMapper {

    private VisitorMapper(){}

    public static VisitorDTO mapToDTO(Visitor visitor){
        VisitorDTO visitorDTO = new VisitorDTO();
        visitorDTO.setId(visitor.getId());
        visitorDTO.setName(visitor.getName());
        visitorDTO.setSurname(visitor.getSurname());
        visitorDTO.setAge(visitor.getAge());
        visitorDTO.setEmail(visitor.getEmail());
        visitorDTO.setPassword(visitor.getPassword());
        visitorDTO.setSex(visitor.getSex());
        visitorDTO.setSubscription(visitor.getSubscription());
        visitorDTO.setCoach(visitor.getCoach());
        return visitorDTO;
    }

    public static Visitor mapFromDTO(VisitorDTO visitorDTO){
        final Visitor visitor = new Visitor();
        visitor.setId(visitorDTO.getId());
        visitor.setName(visitorDTO.getName());
        visitor.setSurname(visitorDTO.getSurname());
        visitor.setAge(visitorDTO.getAge());
        visitor.setEmail(visitorDTO.getEmail());
        visitor.setPassword(visitorDTO.getPassword());
        visitor.setSex(visitorDTO.getSex());
        visitor.setSubscription(visitorDTO.getSubscription());
        visitor.setCoach(visitorDTO.getCoach());
        return visitor;
    }
}
