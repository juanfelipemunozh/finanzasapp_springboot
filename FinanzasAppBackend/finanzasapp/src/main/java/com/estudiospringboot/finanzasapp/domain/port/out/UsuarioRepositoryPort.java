package com.estudiospringboot.finanzasapp.domain.port.out;

import java.util.Optional;
import java.util.UUID;

import com.estudiospringboot.finanzasapp.domain.model.Usuario;

public interface UsuarioRepositoryPort {

    Usuario guardarUsuario(Usuario usuario);
    Optional<Usuario> obtenerUsuarioPorId(UUID id);
    Optional<Usuario> obtenerUsuarioPorEmail(String correo);
    boolean existePorEmail(String correo);

}
