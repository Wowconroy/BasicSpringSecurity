package com.example.hugespringsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.hugespringsecurity.security.ApplicationUserPermission.COURSE_WRITE;
import static com.example.hugespringsecurity.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(COURSE_WRITE.name())
                .antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(COURSE_WRITE.name())
                .antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(COURSE_WRITE.name())
                .antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ADMIN.name(), ADMIN_TRAINEE.name())
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("anna")
                        .password(passwordEncoder().encode("student"))
//                        .roles(STUDENT.name())
                        .authorities(STUDENT.getGrantedAuthority())
                        .build(),
                User.builder()
                        .username("linda")
                        .password(passwordEncoder().encode("admin"))
//                        .roles(ADMIN.name())
                        .authorities(ADMIN.getGrantedAuthority())
                        .build(),
                User.builder()
                        .username("tom")
                        .password(passwordEncoder().encode("admin"))
//                        .roles(ADMIN_TRAINEE.name())
                        .authorities(ADMIN_TRAINEE.getGrantedAuthority())
                        .build()
        );
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
