package com.jongsik2.training.gymate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) ->
                        csrf.disable()
                )
                .headers((header) -> header.frameOptions(frameOptionsConfig ->
                        frameOptionsConfig.disable())
                )
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/login", "/signup").permitAll()
                                .requestMatchers("/api/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin((formLogin) ->
                        formLogin
                                .loginPage("/login")
                                .defaultSuccessUrl("/")
                )
        ;

        return http.build();
    }
}
