package com.example.garbandgo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Позволяваме публичен достъп до login страницата, aboutUs и статични ресурси
                        .requestMatchers("/","/login", "/aboutUs", "/css/**", "/js/**", "/images/**", "/register")
                        .permitAll()
                        // Всички останали заявки изискват автентикация
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")            // URL на custom login страницата
                        .loginProcessingUrl("/login")   // URL, към който се изпраща формата с POST
                        .defaultSuccessUrl("/aboutUs", true) // При успешен логин винаги се пренасочва към /aboutUs
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable());       // Деактивира CSRF (ползвайте с внимание)

        return http.build();
    }
}
