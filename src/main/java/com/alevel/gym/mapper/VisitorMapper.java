package com.alevel.gym.mapper;

import com.alevel.gym.dto.VisitorDTO;
import com.alevel.gym.model.Visitor;

public class VisitorMapper {

    private VisitorMapper() {
    }

    public static Visitor mapFromDTO(VisitorDTO visitorDTO) {
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
