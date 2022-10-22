package com.alevel.gym.service;

import com.alevel.gym.dto.VisitorDTO;
import com.alevel.gym.mapper.VisitorMapper;
import com.alevel.gym.model.StatusPeople;
import com.alevel.gym.model.Visitor;
import com.alevel.gym.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorService implements UserDetailsService {

    private VisitorRepository visitorRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public VisitorService(VisitorRepository visitorRepository, PasswordEncoder passwordEncoder) {
        this.visitorRepository = visitorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return visitorRepository.findByEmail(email);
    }

    private void getPasswordEncoder(Visitor visitor) {
        String password = visitor.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        visitor.setPassword(encodedPassword);
    }

    public void saveVisitor(VisitorDTO visitorDTO) {
        Visitor visitor = VisitorMapper.mapFromDTO(visitorDTO);
        visitor.setStatusPeople(StatusPeople.VISITOR);
        getPasswordEncoder(visitor);
        visitorRepository.save(visitor);
    }

    public void saveVisitor(Visitor visitor) {
        visitor.setStatusPeople(StatusPeople.VISITOR);
        getPasswordEncoder(visitor);
        visitorRepository.save(visitor);
    }

    public Visitor findByEmail(String email) {
        return visitorRepository.findByEmail(email);
    }

    public void saveAdmin(VisitorDTO visitorDTO) {
        Visitor visitor = VisitorMapper.mapFromDTO(visitorDTO);
        visitor.setStatusPeople(StatusPeople.ADMIN);
        getPasswordEncoder(visitor);
        visitorRepository.save(visitor);
    }

    public Iterable<Visitor> getAll() {
        return visitorRepository.findAll();
    }


    public List<Visitor> findAllByStatusPeopleAdmins() {
        return visitorRepository.findAllByStatusPeople(StatusPeople.ADMIN.name());
    }

    public void deleteById(String id) {
        visitorRepository.deleteById(id);
    }

    public Visitor updateVisitor(Visitor visitor) {
        visitor.setStatusPeople(StatusPeople.VISITOR);
        return visitorRepository.save(visitor);
    }

    public Visitor updateAdmin(Visitor visitor) {
        visitor.setStatusPeople(StatusPeople.ADMIN);
        return visitorRepository.save(visitor);
    }

    public Visitor findById(String id) {
        return visitorRepository.findById(id).orElseThrow(IllegalAccessError::new);
    }

    public List<Visitor> findAllVisitors() {
        return visitorRepository.findAllByStatusPeople(StatusPeople.VISITOR.name());
    }
}
