package com.estudiospringboot.finanzasapp.infrastructure.persistence.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.estudiospringboot.finanzasapp.domain.model.token.EmailVerificationToken;
import com.estudiospringboot.finanzasapp.domain.port.out.VerificationTokenRepositoryPort;
import com.estudiospringboot.finanzasapp.infrastructure.persistence.mapper.VerificationTokenMapper;
import com.estudiospringboot.finanzasapp.infrastructure.persistence.repository.VerificationTokenJpaRepository;

@Component
public class VerificationTokenRepositoryAdapter implements VerificationTokenRepositoryPort {

    private final VerificationTokenJpaRepository repository;

    public VerificationTokenRepositoryAdapter(VerificationTokenJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void guardar(EmailVerificationToken token) {
        repository.save(VerificationTokenMapper.toEntity(token));
    }

    @Override
    public Optional<EmailVerificationToken> encuentraPorToken(String token) {
        return repository.findById(token).map(VerificationTokenMapper::toDomain);
    }

    @Override
    public void eliminar(String token) {
        repository.deleteById(token);
    }
}
