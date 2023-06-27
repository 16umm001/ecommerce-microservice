package com.ecommerce.accounts.users.models;

import com.ecommerce.accounts.auth.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name = "users_profile")
@NoArgsConstructor
public class UserProfile {
    @Id
    private long id;

    @Size(max = 128)
    private String firstName;

    @Size(max = 128)
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    public UserProfile(long id, String firstName, String lastName, User user) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
    }
}
