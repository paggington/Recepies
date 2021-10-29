package com.example.recepies.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class User implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private boolean isActive;
    private List<GrantedAuthority> grantedAuthorityList;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorityList;
    }
    public User(AppUser user){
        this.username=user.getUsername();
        this.isActive=user.isActive();
        this.password=user.getPassword();
        this.grantedAuthorityList= Arrays.stream(user.getRole().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        this.id=user.getId();
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return isActive;
    }
}
