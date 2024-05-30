package com.example.diploma.security;

import com.example.diploma.entity.Role;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Getter
public class PrincipalUser implements UserDetails {
    private final Integer id;
    private final String email;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;

    private PrincipalUser(Integer id, String email, String password,
                          List<SimpleGrantedAuthority> authorities,
                          boolean isAccountNonExpired, boolean isAccountNonLocked,
                          boolean isCredentialsNonExpired, boolean isEnabled) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public static PrincipalUser of(com.example.diploma.entity.User user) {
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .toList();

        return new PrincipalUser(user.getId(), user.getEmail(), user.getPassword(), authorities,
                true, true, true, true);
    }

    @Override
    public String getUsername() {
        return email;
    }

}
