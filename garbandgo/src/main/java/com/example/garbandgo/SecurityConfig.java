package com.example.garbandgo;

import com.example.garbandgo.service.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomAuthenticationSuccessHandler successHandler;

    public SecurityConfig(CustomAuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/aboutUs", "/contact", "/register", "/login", "/css/**", "/images/**", "/js/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/products/**", "/restaurants/**").permitAll()
                        .requestMatchers("/user/**", "/cart/**", "/products/shop/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/cart/checkout").hasRole("USER")
                        .requestMatchers("/manager/**","/orders/**", "/products/manage/**", "/products/add/**", "/products/edit/**", "/products/delete/**").hasRole("MANAGER")
                        .requestMatchers("/restaurants/edit/**", "/restaurants/delete/**").hasRole("MANAGER")
                        .requestMatchers("/rest_owner/**").hasRole("REST_OWNER")
                        .requestMatchers("/admin/**", "/promocodes/**").hasRole("ADMIN")
                        .requestMatchers("/products/manage/**", "/products/add/**", "/products/edit/**", "/products/delete/**").hasRole("ADMIN")
                        .requestMatchers("/restaurants/edit/**", "/restaurants/delete/**").hasRole("ADMIN")
                        .requestMatchers("/orders/**").hasRole("ADMIN")
                        .requestMatchers("/courier/**", "/orders/**").hasRole("COURIER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(successHandler)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .sessionManagement(session -> session
                        .sessionFixation().migrateSession()
                        .invalidSessionUrl("/login?sessionExpired=true")
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
