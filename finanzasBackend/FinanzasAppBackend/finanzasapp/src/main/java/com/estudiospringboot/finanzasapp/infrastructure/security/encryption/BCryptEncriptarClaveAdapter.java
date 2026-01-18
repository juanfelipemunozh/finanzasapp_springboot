package com.estudiospringboot.finanzasapp.infrastructure.security.encryption;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.estudiospringboot.finanzasapp.domain.service.EncriptadorClaveService;

@Component
public class BCryptEncriptarClaveAdapter implements EncriptadorClaveService {

    private final BCryptPasswordEncoder passwordEncoder;

    public BCryptEncriptarClaveAdapter() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encriptarClave(String claveSinEncriptar) {
        return passwordEncoder.encode(claveSinEncriptar);
    }

    @Override
    public boolean coinciden(String claveSinEncriptar, String claveEncriptada) {
        return passwordEncoder.matches(claveSinEncriptar, claveEncriptada);
    }

}
