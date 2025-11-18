package org.example.seoulcitytourdemo.cofing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // 누구나 접근 가능
                        .requestMatchers("/", "/login", "/guide/login", "/guide/logout",
                                "/css/**", "/js/**", "/images/**", "/error").permitAll()

                        // 가이드 관련 전부 허용 (우리가 세션으로 직접 관리)
                        .requestMatchers("/guide/**").permitAll()

                        .anyRequest().authenticated()
                )
                // Spring Security 기본 로그인 완전 꺼버림
                .formLogin(form -> form.disable())
                .logout(logout -> logout.disable());

        return http.build();
    }
}