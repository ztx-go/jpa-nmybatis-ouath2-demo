package com.example.jpamybatisplusdemo.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.LinkedList;
import java.util.List;

public class CustomUserSecurityDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> authorities = new LinkedList<>();
        UserDetails securityDetails = new User(account, "", authorities);
        return securityDetails;
    }
}
