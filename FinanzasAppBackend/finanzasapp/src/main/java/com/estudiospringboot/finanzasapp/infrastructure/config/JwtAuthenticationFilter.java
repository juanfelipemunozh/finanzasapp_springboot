package com.estudiospringboot.finanzasapp.infrastructure.config;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.estudiospringboot.finanzasapp.domain.model.Usuario;
import com.estudiospringboot.finanzasapp.domain.port.out.JwtTokenPort;
import com.estudiospringboot.finanzasapp.domain.port.out.UsuarioRepositoryPort;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenPort jwtTokenPort;
    private final UsuarioRepositoryPort usuarioRepository;

    public JwtAuthenticationFilter(
            JwtTokenPort jwtTokenPort,
            UsuarioRepositoryPort usuarioRepository) {
        this.jwtTokenPort = jwtTokenPort;
        this.usuarioRepository = usuarioRepository;
    };

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        String correo = jwtTokenPort.extraerNombreUsuario(token);

        Usuario usuario = usuarioRepository
                .obtenerUsuarioPorEmail(correo)
                .orElse(null);

        if (usuario != null && jwtTokenPort.esTokenValido(token, usuario)) {

            List<SimpleGrantedAuthority> authorities = usuario.getRoles().stream()
            .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.name())).toList();

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                usuario, 
                null,
                authorities
            );

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        };

        filterChain.doFilter(request, response);
    }
}
