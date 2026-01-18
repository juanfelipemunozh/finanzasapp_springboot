package com.estudiospringboot.finanzasapp.infrastructure.adapter.out.security;

import java.security.Key;
import java.util.*;

import org.springframework.stereotype.Component;

import com.estudiospringboot.finanzasapp.domain.model.Usuario;
import com.estudiospringboot.finanzasapp.domain.port.out.JwtTokenPort;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenAdapter implements JwtTokenPort {

    private static final String SECRET =
            "ez0Bufg/btgoEJvcSmidWMBl6ckQ0svrBFXcIbicnlc=";

    private static final long EXPIRATION_TIME = 86400000; // 1 día en milisegundos

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }
    

    @Override
    public String generarToken(Usuario usuario) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(usuario.getEmail())
                .claim("roles", usuario.getRoles().stream().map(Enum::name).toList())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String generarTokenString(String correo) {
        return Jwts.builder()
                .setSubject(correo)
                .claim("roles", "PRUEBA ROL")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String extraerNombreUsuario(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    @Override
    public boolean esTokenValido(String token, Usuario usuario) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject().equals(usuario.getEmail())
                    && claims.getExpiration().after(new Date());

        } catch (Exception e) {
            return false;
        }
    };

}
