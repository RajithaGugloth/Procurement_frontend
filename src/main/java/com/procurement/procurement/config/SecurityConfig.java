package com.procurement.procurement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // Public
                        .requestMatchers("/login", "/register").permitAll()

                        // Role based pages
                        .requestMatchers("/vendor/**")
                        .hasAnyRole("ADMIN","PROCUREMENT_MANAGER")

                        .requestMatchers("/requisition/**")
                        .hasAnyRole("ADMIN","EMPLOYEE","PROCUREMENT_MANAGER")

                        .requestMatchers("/purchase-order/**")
                        .hasAnyRole("ADMIN","PROCUREMENT_MANAGER")

                        .requestMatchers("/approval/**")
                        .hasAnyRole("ADMIN","PROCUREMENT_MANAGER")

                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}