package com.example.awstest.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailServiceImpl userDetailService;

    public SecurityConfiguration(UserDetailServiceImpl userDetailService) {
        this.userDetailService = userDetailService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/orders/stages/remains").permitAll()
                .antMatchers(HttpMethod.POST, "/test/wf").permitAll()
                .antMatchers(HttpMethod.GET, "/test/wf").permitAll()
                .antMatchers("/web/home").permitAll()
                .antMatchers("/web/products/**").hasRole("ADMIN")
                .antMatchers("/web/orders**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/web/reports/**").hasAnyRole("USER", "ADMIN")
                .and()
                .csrf().disable()
                .formLogin()
                .and()
                .exceptionHandling().accessDeniedPage("/web/error");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }
}
