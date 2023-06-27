package com.ecommerce.accounts.auth.service;


import com.ecommerce.accounts.auth.Repository.UserAuthRepository;
import com.ecommerce.accounts.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {

    @Autowired
    private UserAuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(long id, String email, String password){
        User user = new User(id, email, passwordEncoder.encode(password));
        authRepository.save(user);
        return user;
    }

}
