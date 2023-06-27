package com.ecommerce.accounts.auth.service;

import com.ecommerce.accounts.auth.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailImplement implements UserDetails {

    private long id;

    private String email;

    private Collection<? extends GrantedAuthority> authorities;

    @JsonIgnore
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public long getId(){
        return this.id;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass())
            return false;
        UserDetailImplement user = (UserDetailImplement) obj;
        return Objects.equals(id, user.id);
    }

    public UserDetailImplement(long id, String email, Collection<? extends GrantedAuthority> authorities, String password) {
        this.id = id;
        this.email = email;
        this.authorities = authorities;
        this.password = password;
    }

    public static UserDetailImplement build(User user){
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailImplement(
                user.getId(),
                user.getEmail(),
                authorities,
                user.getPassword()
        );
    }
}
