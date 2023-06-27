package com.ecommerce.accounts.auth.service;

import com.ecommerce.accounts.auth.Repository.UserAuthRepository;
import com.ecommerce.accounts.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userAuthRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found with this email!")
        );

        return UserDetailImplement.build(user);
    }
}
