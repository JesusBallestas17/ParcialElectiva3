package com.example.TallerParcialSpringBootJPA.security;

import com.example.TallerParcialSpringBootJPA.entities.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {
    private Integer id;
    private String email;
    private String password;

    public UserPrincipal(Integer id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public static UserPrincipal create(Usuario usuario) {
        return new UserPrincipal(
                usuario.getIdUsuario(),
                usuario.getCorreoElectronico(),
                usuario.getContrasena()
        );
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
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
}