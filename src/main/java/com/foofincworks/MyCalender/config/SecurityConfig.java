package com.foofincworks.MyCalender.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                    //removes security from given APIs ("/**" -> all levels below)
                    (requests) -> requests
                            .antMatchers("/admin/**").hasRole("ADMIN")
                            .antMatchers("/events/**", "/assets/**", "/css/**").permitAll()
                            .anyRequest().authenticated()
            )
            .formLogin((form) -> form.permitAll()
            )
            .logout((logout) -> logout
                    .logoutSuccessUrl("/events/list")
                    .permitAll());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        //Sets up password encryption
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        //Set up and build in-memory profile(s)
        UserDetails admin = users
                .username("foof")//foof
                .password("dog")//dog
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }



    //TODO Add password encryption, use DB for best practice?
}
