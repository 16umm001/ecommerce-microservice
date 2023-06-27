package com.ecommerce.accounts.users.service;


import com.ecommerce.accounts.auth.Repository.UserAuthRepository;
import com.ecommerce.accounts.auth.model.User;
import com.ecommerce.accounts.auth.service.UniqueIdGeneratorService;
import com.ecommerce.accounts.auth.service.UserDetailImplement;
import com.ecommerce.accounts.users.apis.v1.payload.AddressRequest;
import com.ecommerce.accounts.users.apis.v1.utils.AddressMapper;
import com.ecommerce.accounts.users.dto.AddressResponse;
import com.ecommerce.accounts.users.exceptions.ProfileNotFoundException;
import com.ecommerce.accounts.users.models.Address;
import com.ecommerce.accounts.users.models.AddressType;
import com.ecommerce.accounts.users.models.UserProfile;
import com.ecommerce.accounts.users.respository.AddressRepository;
import com.ecommerce.accounts.users.respository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private UniqueIdGeneratorService uniqueIdGeneratorService;

    @Autowired
    private UserAuthRepository authRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public AddressResponse createAddress(AddressRequest addressRequest) throws ProfileNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailImplement userDetails = (UserDetailImplement) auth.getPrincipal();
        String email = userDetails.getUsername();
        User user = authRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found with this email!")
        );
        long id = uniqueIdGeneratorService.getId();

        UserProfile userProfile = userProfileRepository.findByUser(user);
        if(userProfile ==null){
            throw new ProfileNotFoundException("Please complete user profile!");
        }

        String address_field1 = addressRequest.getAddress_field1();
        String address_field2 = addressRequest.getAddress_field2();
        String city = addressRequest.getCity();
        String country = addressRequest.getCountry();
        String pinCode = addressRequest.getPinCode();
        AddressType addressType = addressRequest.getAddressType();
        boolean is_default_address = addressRequest.isDefaultAddress();

        Address address = new Address(
                id,
                address_field1,
                address_field2,
                city,
                country,
                pinCode,
                addressType,
                is_default_address
        );
        address.setProfile(userProfile);
        addressRepository.save(address);
        return new AddressResponse(id, address_field1, address_field2, city, country, pinCode, addressType, is_default_address);
    }

    public List<AddressResponse> listAddress(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailImplement userDetails = (UserDetailImplement) auth.getPrincipal();
        String email = userDetails.getUsername();
        User user = authRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found with this email!")
        );

        UserProfile userProfile = userProfileRepository.findByUser(user);
        List<Address> addresses = addressRepository.findAllByProfile(userProfile);
        return AddressMapper.INSTANCE.addressToAddressResponse(addresses);
    }
}
