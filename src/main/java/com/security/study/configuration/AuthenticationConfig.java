package com.security.study.configuration;

import com.security.study.configuration.filter.JwtTokenFilter;
import com.security.study.exception.CustomAuthenticationEntryPoint;
import com.security.study.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class AuthenticationConfig {

    private final UserService userService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .httpBasic().disable()
            .formLogin().disable()
            .csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests()
            .requestMatchers("/verify/*").authenticated()
            .requestMatchers("/auth/login").authenticated()
            .requestMatchers("/user").authenticated()// TODO post인 경우로 변경
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(new CustomAuthenticationEntryPoint()) // 인증되지 않은 사용자 핸들러
            .and()
            .addFilterBefore(new JwtTokenFilter(userService), UsernamePasswordAuthenticationFilter.class)//토큰으로 사용자를 인증하기 이해
        ;
        return httpSecurity.build();
    }
}
