package com.ecommerce.accounts.auth.api.v1.controller;


import com.ecommerce.accounts.auth.api.v1.payload.UserLoginRequest;
import com.ecommerce.accounts.auth.api.v1.payload.UserRegisterRequest;
import com.ecommerce.accounts.auth.dto.MessageResponse;
import com.ecommerce.accounts.auth.dto.UserLoginSuccessResponse;
import com.ecommerce.accounts.auth.service.UniqueIdGeneratorService;
import com.ecommerce.accounts.auth.service.UserAuthService;
import com.ecommerce.accounts.auth.util.JwtHelper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users/auth")
public class UserAuthenticationController {

    @Value("${unique_id.uri}")
    private String uri;

    @Autowired
    private UniqueIdGeneratorService uniqueIdGeneratorService;

    @Autowired
    private UserAuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterRequest userRegisterRequest){
        long id = uniqueIdGeneratorService.getId();
        String email = userRegisterRequest.getEmail();
        String password = userRegisterRequest.getPassword();
        authService.registerUser(id, email, password);
        MessageResponse messageResponse = new MessageResponse("User Registration Completed!");
        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtHelper.generateToken(authentication);
        UserLoginSuccessResponse loginSuccessResponse = new UserLoginSuccessResponse(
            jwt
        );
        return ResponseEntity.ok(loginSuccessResponse);
    }
}
