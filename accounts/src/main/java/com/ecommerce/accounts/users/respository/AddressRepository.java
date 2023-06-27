package com.ecommerce.accounts.users.respository;

import com.ecommerce.accounts.users.dto.AddressResponse;
import com.ecommerce.accounts.users.models.Address;
import com.ecommerce.accounts.users.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByProfile(UserProfile userProfile);

}
