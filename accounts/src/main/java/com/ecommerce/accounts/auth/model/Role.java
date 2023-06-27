package com.ecommerce.accounts.auth.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    private long id;

    @Enumerated(EnumType.STRING)
    private ERole name;
}
