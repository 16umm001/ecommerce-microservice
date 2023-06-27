package com.ecommerce.accounts.users.apis.v1.controllers;


import com.ecommerce.accounts.users.apis.v1.payload.ProfileRequest;
import com.ecommerce.accounts.users.dto.ProfileResponse;
import com.ecommerce.accounts.users.exceptions.ProfileNotFoundException;
import com.ecommerce.accounts.users.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/me")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping
    public ProfileResponse create(@Valid @RequestBody ProfileRequest profileRequest){
        return profileService.createProfile(profileRequest);
    }

    @PutMapping("/{id}")
    public ProfileResponse update(@PathVariable("id") long id, @Valid @RequestBody ProfileRequest profileRequest) throws ProfileNotFoundException {
        return profileService.updateProfile(id, profileRequest);
    }

    @GetMapping
    public ProfileResponse retrieve(){
        return profileService.retrieveProfile();
    }
}
