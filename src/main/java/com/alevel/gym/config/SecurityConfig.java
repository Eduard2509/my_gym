package com.alevel.gym.config;

import com.alevel.gym.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final VisitorService visitorService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(VisitorService visitorService, PasswordEncoder passwordEncoder) {
        this.visitorService = visitorService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/menu", "/login", "/sign-up", "/coaches", "/about", "/pricing",
                        "/crud-photo/*", "/styles/*", "/gym-photo/*", "/design-site/*", "/card-photo/*",
                        "/coach-photo/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/")
                .loginPage("/login")
                .permitAll()
                .and()
                .rememberMe()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll()
                .invalidateHttpSession(true)
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(visitorService)
                .passwordEncoder(passwordEncoder);
    }

}
