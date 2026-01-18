package com.estudiospringboot.finanzasapp.domain.model;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Usuario implements UserDetails {

    private final UUID id;
    private String nombre;
    private String email;
    private String clave;
    private boolean autorizado;
    private AuthProvider provider;
    private Set<Rol> roles;

    public Usuario(UUID id, String nombre, String email, String clave, boolean autorizado, AuthProvider provider,
            Set<Rol> roles) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
        this.autorizado = autorizado;
        this.provider = provider;
        this.roles = roles;
    }

    // Crear usuario local
    public static Usuario crearUsuarioLocal(String nombre, String email, String claveEncriptada) {
        return new Usuario(
                UUID.randomUUID(),
                nombre,
                email,
                claveEncriptada,
                false,
                AuthProvider.LOCAL,
                Set.of(Rol.USUARIO));
    }

    // Crear usuario OAuth con autenicacion google o apple
    public static Usuario crearUsuarioOath(String nombre, String email, AuthProvider provider) {
        return new Usuario(
                UUID.randomUUID(),
                nombre,
                email,
                null,
                true,
                provider,
                Set.of(Rol.USUARIO));
    }

    // Validar autorización usuario
    public void autorizado(){
        this.autorizado = true;
    }

    public boolean esAdmin(){
        return this.roles.contains(Rol.ADMIN);
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isAutorizado() {
        return autorizado;
    }

    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return roles.stream()
        .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.name())).toList();
    }

    @Override
    public @Nullable String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return email;
    }

}
