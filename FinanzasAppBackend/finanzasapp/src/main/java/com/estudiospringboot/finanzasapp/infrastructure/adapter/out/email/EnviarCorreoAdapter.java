package com.estudiospringboot.finanzasapp.infrastructure.adapter.out.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import com.estudiospringboot.finanzasapp.domain.port.out.EnviarEmailPort;

@Component
public class EnviarCorreoAdapter implements EnviarEmailPort {

    private static final Logger log = LoggerFactory.getLogger(EnviarCorreoAdapter.class);

    @Override
    public void enviarEmail(String destinatario, String asunto, String cuerpoMensaje) {
        log.info("Enviando mensaje a: {}", destinatario);
        log.info("Asunto: {}", asunto);
        log.info("Cuerpo del mensaje: {}", cuerpoMensaje);
    }
}
