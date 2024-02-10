package com.project.ecommerce_youtube.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class AppConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/api/**").authenticated() // Fix the method name to authorizeRequests
                        .anyRequest().permitAll())
                .addFilterBefore(new JWTValidator(), BasicAuthenticationFilter.class) // You need to replace null with your custom filter
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource()) // Use a separate method for CorsConfigurationSource
                .and()
                .httpBasic()
                .and()
                .formLogin();

        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
            config.setAllowCredentials(true);
            config.setAllowedMethods(Arrays.asList("*"));
            config.setAllowedHeaders(Arrays.asList("*"));
            config.setExposedHeaders(Arrays.asList("Authorization"));
            config.setMaxAge(3600L);
            return config;
        };

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
