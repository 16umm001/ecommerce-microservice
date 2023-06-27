package com.ecommerce.accounts.users.apis.v1.controllers;


import com.ecommerce.accounts.users.apis.v1.payload.AddressRequest;
import com.ecommerce.accounts.users.dto.AddressResponse;
import com.ecommerce.accounts.users.exceptions.ProfileNotFoundException;
import com.ecommerce.accounts.users.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public AddressResponse create(@Valid @RequestBody AddressRequest addressRequest) throws ProfileNotFoundException {
        return addressService.createAddress(addressRequest);
    }

    @GetMapping
    public List<AddressResponse> list(){
        return addressService.listAddress();
    }
}
