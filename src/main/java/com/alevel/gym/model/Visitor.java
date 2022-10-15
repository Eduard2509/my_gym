package com.alevel.gym.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class Visitor extends People implements UserDetails {

    @Min(12)
    @Max(65)
    private int age;
    @Enumerated(EnumType.STRING)
    private StatusPeople statusPeople;
    @Email
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;


    public Visitor(String id, String name, String surname, int age, String email, String password, Sex sex) {
        super(id, name, surname, sex);
        this.age = age;
        this.statusPeople = StatusPeople.VISITOR;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(statusPeople);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
