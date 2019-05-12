package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class securityconfig extends WebSecurityConfigurerAdapter {
    @Autowired
    successhandler shandler;

    @Autowired
    unauthorized unauthorize;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                // temporary fix
                .exceptionHandling().authenticationEntryPoint(unauthorize)
                .and()
                .authorizeRequests()
                .antMatchers("/jpalogin","/sslogin","/jpabooklist").permitAll()
                .antMatchers("/jpashowcart","/jpacleancart","/jpaaddtocart","/jpashowhistory","/jpadeleteorder").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/jpaeditsave","/jpaeditdelete","/jpashowuser").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/sslogin")
                .usernameParameter("id")
                .passwordParameter("password")
                .successHandler(shandler)
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
