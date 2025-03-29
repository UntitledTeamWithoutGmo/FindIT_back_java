package com.findit.FindIt.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig  {
    @Autowired
    private UserDetailsService userDetailsService;
    private JwtFilter filter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf-> csrf.disable())
                .cors(cors-> Customizer.withDefaults())
                .authorizeHttpRequests(auth ->
                auth.requestMatchers("/api/users/update/{id}").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                        .requestMatchers("/api/users/porn").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/api/users/profile").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers("/api/users/delete{id}").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                        .requestMatchers("/api/recruiter/porn2").hasAnyAuthority("ROLE_RECRUITER", "ROLE_ADMIN")
                        .requestMatchers("/api/users/register").permitAll()
                        .requestMatchers("/api/users/login").permitAll()
                        .requestMatchers("/api/users/all").permitAll()
                        .requestMatchers("/api/users/{id}").permitAll()
                        .requestMatchers("/api/recruiter/jwt").permitAll()
                        .requestMatchers("/api/recruiter/profile").hasAnyAuthority("ROLE_RECRUITER", "ROLE_ADMIN")
                        .requestMatchers("/api/vacancy/create").hasAnyAuthority("ROLE_RECRUITER", "ROLE_ADMIN")
                        .requestMatchers("/api/vacancy/all").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()

                .anyRequest().permitAll())
                .sessionManagement(sessiojnManagment-> sessiojnManagment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception -> exception
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);




        return  http.build();



    }




    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider =  new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }



}
