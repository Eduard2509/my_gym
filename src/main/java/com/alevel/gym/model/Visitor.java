package com.alevel.gym.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Visitor extends People implements UserDetails {

    @Min(value = 12, message = "You are too young to go to the gym")
    @Max(value = 65, message = "You are too old to go to the gym")
    private int age;
    @Enumerated(EnumType.STRING)
    private StatusPeople statusPeople;
    @Email(message = "This email is invalid")
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    @OneToOne
    @JoinColumn(name = "locked_id")
    private LockedRoom lockedRoom;


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
