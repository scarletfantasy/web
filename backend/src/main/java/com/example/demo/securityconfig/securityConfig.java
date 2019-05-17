package com.example.demo.securityconfig;

import com.example.demo.entrypoint.unauthorized;
import com.example.demo.handler.failHandler;
import com.example.demo.handler.logoutsuccesshandler;
import com.example.demo.handler.successHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    successHandler shandler;
    @Autowired
    failHandler fhandler;
    @Autowired
    unauthorized unauthorize;
    @Autowired
    logoutsuccesshandler lshandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorize)
                .and()
                .authorizeRequests()
                .antMatchers("/jpalogin","/sslogin","/jpabooklist","/jparegister","/img/*","/jpacurrentuser").permitAll()
                .antMatchers("/jpashowcart","/jpacleancart","/jpaaddtocart","/jpashowhistory","/jpadeleteorder").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/jpaeditsave","/jpaeditdelete","/jpashowuser").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/sslogin")
                .usernameParameter("id")
                .passwordParameter("password")
                .successHandler(shandler)
                .failureHandler(fhandler)
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessHandler(lshandler)
                .invalidateHttpSession(true);

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
