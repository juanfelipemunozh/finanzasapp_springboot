package com.estudiospringboot.finanzasapp.domain.service;

public interface EncriptadorClaveService {

    String encriptarClave(String claveSinEncriptar);

    boolean coinciden(String claveSinEncriptar, String claveEncriptada);

}
