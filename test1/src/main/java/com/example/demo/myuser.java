package com.example.demo;
import com.example.demo.dao.*;
import com.example.demo.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
@Component
public class myuser  implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    userDao userdao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        if(userdao.getuserbyid(username).isPresent())
        {
            User user=userdao.getuserbyid(username).get();
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
            org.springframework.security.core.userdetails.User user1=new org.springframework.security.core.userdetails.User
                    (user.getId(),passwordEncoder.encode(user.getPassword()),true,true,true,true,authorities);
            return user1;
        }
        else
        {
            return null;
        }
    }
}
