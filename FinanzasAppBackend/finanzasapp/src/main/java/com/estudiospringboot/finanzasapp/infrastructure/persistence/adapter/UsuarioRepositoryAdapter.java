package com.estudiospringboot.finanzasapp.infrastructure.persistence.adapter;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.estudiospringboot.finanzasapp.domain.model.Usuario;
import com.estudiospringboot.finanzasapp.domain.port.out.UsuarioRepositoryPort;
import com.estudiospringboot.finanzasapp.infrastructure.persistence.mapper.UsuarioPersistenceMapper;
import com.estudiospringboot.finanzasapp.infrastructure.persistence.repository.UsuarioJpaRepository;

@Component
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort  {

    private final UsuarioJpaRepository jpaRepository;

    public UsuarioRepositoryAdapter(UsuarioJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return UsuarioPersistenceMapper.toDomain(
            jpaRepository.save(
                UsuarioPersistenceMapper.toEntiy(usuario)
            )
        );
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorId(UUID id) {
        return jpaRepository.findById(id).map(UsuarioPersistenceMapper::toDomain);
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorEmail(String email) {
        return jpaRepository.findByEmail(email).map(UsuarioPersistenceMapper::toDomain);
    }

    @Override
    public boolean existePorEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }
}