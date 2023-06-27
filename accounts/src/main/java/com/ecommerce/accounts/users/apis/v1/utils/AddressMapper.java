package com.ecommerce.accounts.users.apis.v1.utils;


import com.ecommerce.accounts.users.dto.AddressResponse;
import com.ecommerce.accounts.users.models.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(ignore = true, target = "profile")
    List<AddressResponse> addressToAddressResponse(List<Address> address);
}
