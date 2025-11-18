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
                // CSRF 끄기 (API + 세션 로그인 쓰니까 필수)
                .csrf(csrf -> csrf.disable())

                // 모든 요청 허용 (우리가 직접 세션으로 로그인 관리함)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()   // ← 이 한 줄이 핵심!
                )

                // Spring Security 기본 로그인 완전 비활성화
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .logout(logout -> logout.disable())

                // 세션은 우리가 직접 관리하니까 정책 변경
                .sessionManagement(session -> session
                        .sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.ALWAYS)
                );

        return http.build();
    }
}