package com.ecommerce.accounts.users.respository;

import com.ecommerce.accounts.auth.model.User;
import com.ecommerce.accounts.users.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    public UserProfile findByUser(User user);
}
