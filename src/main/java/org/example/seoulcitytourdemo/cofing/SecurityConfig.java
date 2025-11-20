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
                // CSRF 끄기
                .csrf(csrf -> csrf.disable())

                // 모든 요청 허용
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )

                // 기본 로그인 비활성화
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .logout(logout -> logout.disable())

                // 세션 정책
                .sessionManagement(session -> session
                        .sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.ALWAYS)
                )

                // 이 3줄 추가하면 iframe 문제 완전 해결!!!
                .headers(headers -> headers
                        .frameOptions(frame -> frame.disable())        // X-Frame-Options: DENY 해제
                        .contentSecurityPolicy(csp -> csp.policyDirectives("frame-ancestors 'self'")) // CSP도 허용
                );

        return http.build();
    }
}