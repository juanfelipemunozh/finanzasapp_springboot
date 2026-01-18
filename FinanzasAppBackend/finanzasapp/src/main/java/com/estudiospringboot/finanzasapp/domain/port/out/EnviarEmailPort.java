package com.estudiospringboot.finanzasapp.domain.port.out;

public interface EnviarEmailPort {

    void enviarEmail(String destinatario, String asunto, String cuerpoMensaje);

}
