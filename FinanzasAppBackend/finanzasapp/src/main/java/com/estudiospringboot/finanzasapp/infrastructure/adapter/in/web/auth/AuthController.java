package com.estudiospringboot.finanzasapp.infrastructure.adapter.in.web.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estudiospringboot.finanzasapp.application.dto.usuario.RegistrarUsuarioRequest;
import com.estudiospringboot.finanzasapp.application.usecase.usuario.ConfirmarUsuarioService;
import com.estudiospringboot.finanzasapp.application.usecase.usuario.RegistrarUsuarioService;
import com.estudiospringboot.finanzasapp.domain.model.Usuario;
import com.estudiospringboot.finanzasapp.domain.port.out.LoginUsuarioUseCase;

import com.estudiospringboot.finanzasapp.infrastructure.adapter.in.web.auth.dto.ApiResponse;
import com.estudiospringboot.finanzasapp.infrastructure.adapter.in.web.auth.dto.LoginHttpRequest;
import com.estudiospringboot.finanzasapp.infrastructure.adapter.in.web.auth.dto.LoginHttpResponse;
import com.estudiospringboot.finanzasapp.infrastructure.adapter.in.web.auth.dto.RegistrarUsuarioHttpRequest;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegistrarUsuarioService registrarUsuarioService;
    private final ConfirmarUsuarioService confirmarUsuarioService;
    private final LoginUsuarioUseCase loginUsuarioUseCase;

    public AuthController(RegistrarUsuarioService registrarUsuarioService,
            ConfirmarUsuarioService confirmarUsuarioService, LoginUsuarioUseCase loginUsuarioUseCase) {
        this.registrarUsuarioService = registrarUsuarioService;
        this.confirmarUsuarioService = confirmarUsuarioService;
        this.loginUsuarioUseCase = loginUsuarioUseCase;
    }

    @PostMapping("/registro")
    public ResponseEntity<ApiResponse> registrar(@RequestBody @Valid RegistrarUsuarioHttpRequest request) {

        RegistrarUsuarioRequest registrar = new RegistrarUsuarioRequest(
                request.getNombre(),
                request.getCorreo(),
                request.getClave());

        registrarUsuarioService.registrar(registrar);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(
                        "Usuario registrado exitosamente. Por favor, revisa tu correo para activar la cuenta."));
    }

    @GetMapping("/confirmar")
    public ResponseEntity<ApiResponse> confirmarEmail(@RequestParam("token") String token) {

        confirmarUsuarioService.confirmar(token);

        return ResponseEntity.ok(
                new ApiResponse("Cuenta activada exitosamente."));

    }

    @PostMapping("/login")
    public ResponseEntity<LoginHttpResponse> login(@RequestBody LoginHttpRequest request) {
        
        String token = loginUsuarioUseCase.login(
            request.getCorreo(), 
            request.getClave()
    );

        return ResponseEntity.ok(new LoginHttpResponse(token));
    }

    @GetMapping("/testAuth")
    public String testAuth(Authentication auth) {
        Usuario usuario = (Usuario) auth.getPrincipal();
        return "Hola" + usuario.getEmail();
    }
    
}
