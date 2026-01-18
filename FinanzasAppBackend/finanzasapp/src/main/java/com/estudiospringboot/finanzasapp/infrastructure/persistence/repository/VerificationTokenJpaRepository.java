package com.estudiospringboot.finanzasapp.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudiospringboot.finanzasapp.infrastructure.persistence.entity.VerificationTokenEntity;

public interface VerificationTokenJpaRepository extends JpaRepository<VerificationTokenEntity, String> {

}
