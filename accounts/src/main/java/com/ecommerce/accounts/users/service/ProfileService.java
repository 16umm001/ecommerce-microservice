package com.ecommerce.accounts.users.service;


import com.ecommerce.accounts.auth.Repository.UserAuthRepository;
import com.ecommerce.accounts.auth.model.User;
import com.ecommerce.accounts.auth.service.UniqueIdGeneratorService;
import com.ecommerce.accounts.auth.service.UserDetailImplement;
import com.ecommerce.accounts.users.apis.v1.payload.ProfileRequest;
import com.ecommerce.accounts.users.dto.ProfileResponse;
import com.ecommerce.accounts.users.exceptions.ProfileNotFoundException;
import com.ecommerce.accounts.users.models.UserProfile;
import com.ecommerce.accounts.users.respository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class ProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UniqueIdGeneratorService uniqueIdGeneratorService;

    @Autowired
    private UserAuthRepository authRepository;

    public ProfileResponse createProfile(ProfileRequest profileRequest){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailImplement userDetails = (UserDetailImplement) auth.getPrincipal();
        String email = userDetails.getUsername();
        User user = authRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found with this email!")
        );
        long id = uniqueIdGeneratorService.getId();
        String first_name = profileRequest.getFirst_name();
        String last_name = profileRequest.getLast_name();
        UserProfile userProfile = new UserProfile(
                id, first_name, last_name, user
        );
        UserProfile profile = userProfileRepository.save(userProfile);
        return new ProfileResponse(profile.getId(), user.getEmail(), profile.getFirstName(), profile.getLastName());
    }

    public ProfileResponse updateProfile(long id, ProfileRequest profileRequest) throws ProfileNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailImplement userDetails = (UserDetailImplement) auth.getPrincipal();
        String email = userDetails.getUsername();
        User user = authRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found with this email!")
        );
        UserProfile profile = userProfileRepository.findById(id).orElseThrow(
                ()-> new ProfileNotFoundException("Profile Does not exists")
        );

        profile.setFirstName(profileRequest.getFirst_name());
        profile.setLastName(profileRequest.getLast_name());

        userProfileRepository.save(profile);

        return new ProfileResponse(profile.getId(), user.getEmail(), profile.getFirstName(), profile.getLastName());
    }

    public ProfileResponse retrieveProfile(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailImplement userDetails = (UserDetailImplement) auth.getPrincipal();
        String email = userDetails.getUsername();
        User user = authRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found with this email!")
        );

        UserProfile userProfile = userProfileRepository.findByUser(user);
        if (userProfile == null){
            return new ProfileResponse();
        }
        return new ProfileResponse(userProfile.getId(), user.getEmail(), userProfile.getFirstName(), userProfile.getLastName());

    }
}
