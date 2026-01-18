package com.estudiospringboot.finanzasapp.application.usecase.usuario;

import com.estudiospringboot.finanzasapp.application.dto.usuario.RegistrarUsuarioRequest;
import com.estudiospringboot.finanzasapp.domain.model.Usuario;
import com.estudiospringboot.finanzasapp.domain.model.token.EmailVerificationToken;
import com.estudiospringboot.finanzasapp.domain.port.out.EnviarEmailPort;
import com.estudiospringboot.finanzasapp.domain.port.out.UsuarioRepositoryPort;
import com.estudiospringboot.finanzasapp.domain.port.out.VerificationTokenRepositoryPort;
import com.estudiospringboot.finanzasapp.domain.service.EncriptadorClaveService;

public class RegistrarUsuarioService {

    private final UsuarioRepositoryPort usuarioRepository;
    private final EncriptadorClaveService encriptadorClaveService;
    private final VerificationTokenRepositoryPort verificationRepository;
    private final EnviarEmailPort enviarEmailPort;

    public RegistrarUsuarioService(UsuarioRepositoryPort usuarioRepository,
            EncriptadorClaveService encriptadorClaveService,
            VerificationTokenRepositoryPort verificationRepository,
            EnviarEmailPort enviarEmailPort) {
        this.usuarioRepository = usuarioRepository;
        this.encriptadorClaveService = encriptadorClaveService;
        this.verificationRepository = verificationRepository;
        this.enviarEmailPort = enviarEmailPort;
    }

    public void registrar(RegistrarUsuarioRequest request) {

        if (usuarioRepository.existePorEmail(request.getEmail())) {
            throw new IllegalArgumentException("Usuario ya registrado.");
        }

        String claveEncriptada = encriptadorClaveService.encriptarClave(request.getClave());

        Usuario usuario = Usuario.crearUsuarioLocal(
                request.getNombre(),
                request.getEmail(),
                claveEncriptada);

        usuarioRepository.guardarUsuario(usuario);

        EmailVerificationToken token = EmailVerificationToken.crearToken(usuario.getId());

        verificationRepository.guardar(token);

        String linkConfirmacion = "http://localhost:8080/api/auth/confirmar?token=" + token.getToken();

        enviarEmailPort.enviarEmail(
                usuario.getEmail(),
                "Confirmar tu cuenta",
                "Por favor, confirma tu cuenta haciendo clic en el siguiente enlace:\n" + linkConfirmacion);
    }
}
