Proyecto Springboot finanzas



Para crearlo, este es el prompt inicial enviado



Hola, quiero hacer una app con spring boot backend que me sirve para manejar mis finanzas. Entonces quiero que: 

1\. Permite agregar ingresos 

2\. Permita agregar los egresos 

3\. Permite agregar los gastos fijos 

4\. Que tenga una capa de analisis financiero a futuro basado en datos que le ingrese 

5\. Que tenga otra entidad que haga calculos para saber cual es el saldo actual, conocer el comportamiento de ingresos, egresos, y para que posteriormente en el frontend muestre graficas de diferentes tipos, pero por ahora enfocars en el backend 

6\. Que tenga forma de registrar usuarios, validar el usuario registrado, que envié un correo electrónico y se deba ir a dar click en el link para que quede autorizado para ingresar 

7\. Que tenga seguridad JWT 

8\. Hacer un tipo de ingreso también con google, appleId, etc, y que también identique cuál es el usuario autenticado para que solamente muestre la información que este usuario tiene registrado. 

9\. En la creación de los usuarios, tenga roles. Si es admin, puede visualizar todo, pero es usuario, solo puede ver lo que este ingrese 

10\. Base de dato mysql 



idea es hacer todo con springboot y con arquitectura hexagonal



Estos son los procesos sugeridos para la implementación del sistema:



🚀 Siguiente pasos sugeridos



1️⃣ Crear proyecto Spring Boot

2️⃣ Copiar esta estructura

3️⃣ Implementar User + Roles

4️⃣ JWT

5️⃣ Ingresos / egresos

6️⃣ Análisis financiero

7️⃣ Email

8️⃣ OAuth



✅ 1.1 Crear el proyecto (Spring Initializr)

✅ 1.2 Dependencias iniciales (solo las necesarias)

✔️ Spring Web

✔️ Spring Data JPA

✔️ Spring Security

✔️ Validation

✔️ MySQL Driver

✔️ Lombok



👉 NO agregues OAuth ni Mail aún, eso viene después.



✅ 1.3 Estructura inicial mínima



Esta se genera cuando se crea la aplicación 





✅ 1.4 application.yml base (MySQL + JPA)



Configura desde ya la base de datos:



server:

&nbsp; port: 8080



spring:

&nbsp; datasource:

&nbsp;   url: jdbc:mysql://localhost:3306/finance\_db?useSSL=false\&serverTimezone=UTC

&nbsp;   username: root

&nbsp;   password: root

&nbsp; jpa:

&nbsp;   hibernate:

&nbsp;     ddl-auto: update

&nbsp;   show-sql: true

&nbsp;   properties:

&nbsp;     hibernate:

&nbsp;       format\_sql: true

&nbsp;   open-in-view: false



✅ 1.5 Crear paquetes base (vacíos por ahora)



com.financeapp

│

├── domain

├── application

├── infrastructure

└── api



✅ 1.6 Verificación



Antes de seguir:



✔️ La app levanta sin errores

✔️ Se conecta a MySQL

✔️ No hay código de negocio todavía





Ahora que todo lo anterior está validado que es básicamente la base del proyecto, continuamos en la siguiente etapa:





🔜 PASO 2 — Usuarios + Roles (Dominio puro)



En el siguiente paso vamos a:



Crear User, Role, AuthProvider (DOMINIO)

Sin JPA

Sin Spring

100% arquitectura hexagonal



📁 2.1 Paquetes que vamos a usar



com.financeapp.domain.model.usuario

Usuario.java

Rol.java

AuthProvider.java



Primero creamos la entidad Usuario y los componentes que se van a requerir para autenticación. Esto lo podemos ver en el proyecto en el Domain





🔜 PASO 3 — Puertos del dominio (UsuarioRepository + Clave)



📁 3.1 Paquetes a usar



com.financeapp.domain.port

port

├── in

│   └── Usuario

└── out



👤 3.2 UsuarioRepositoryPort



¿Por que port en el domain? 



Porque:



El dominio define QUÉ necesita

La infraestructura decide CÓMO se implementa

La aplicación orquesta, pero no define contratos técnicos





🔐 3.3 EncriptadorClaveService



📍 com.financeapp.domain.service



📧 3.4 EnviarEmailPort



📍com.financeapp.domain.port.out.EmailSenderPort





🔜 PASO 4 — Casos de Uso: Registro y Login



📁 4.1 Estructura de paquetes



application

├── usecase

│   └── user

│       ├── RegisterUserService.java

│       └── LoginUserService.java   (lo dejamos preparado)

└── dto

&nbsp;   └── user

&nbsp;       ├── RegisterUserRequest.java

&nbsp;       ├── LoginRequest.java

&nbsp;       └── AuthResponse.java





🧾 4.2 DTO — RegistarUsuarioRequest



📍application/dto/user/RegistarUsuarioRequest.java



📌 DTO ≠ Entidad

DTO solo transporta datos. Por eso solo se aplicat Getters





🧠 4.3 Caso de uso — RegistrarUsuarioService



📍 application/usecase/user/RegistrarUsuarioService.java



Observa lo importante aquí

✔️ No hay Spring

✔️ No hay JPA

✔️ No hay anotaciones

✔️ Todo entra por puertos

✔️ Lógica de negocio clara



Esto es hexagonal bien hecha.





🔐 4.4 Preparación para Login (sin implementarlo aún)



📍 application/dto/user/LoginRequest.java

📍 application/dto/user/AuthResponse.java



📌 El JWT vendrá después.



❌ Qué NO hicimos todavía (intencional)



🚫 Controllers

🚫 Spring @Service

🚫 SecurityConfig

🚫 PasswordEncoder real

🚫 Email real



Eso pertenece a infraestructura, no aquí.





✅ Verificación del PASO 4



✔️ Compila sin errores

✔️ Sigue arrancando la app

✔️ No hay imports de Spring

✔️ Arquitectura limpia





🟦 PASO 5 — Infraestructura: Persistencia JPA (User)



📁 5.1 Estructura de paquetes



persistence

├── entity

│   └── UserEntity.java

├── repository

│   └── UserJpaRepository.java

└── adapter

&nbsp;   └── UserRepositoryAdapter.java



🧱 5.2 UserEntity (JPA)



📍 infrastructure/persistence/entity/UsuarioEntity.java





🗄️ 5.3 UserJpaRepository



📍 infrastructure/persistence/repository/UsuarioJpaRepository.java



Spring Data hace el trabajo pesado.





🔌 5.4 UserRepositoryAdapter



📍 infrastructure/persistence/adapter/UsuarioRepositoryAdapter.java



🧠 Claves de este paso (muy importantes)

✔️ El dominio NO sabe que existe JPA

✔️ JPA Entities NO están en dominio

✔️ Mapping explícito (sin magia)

✔️ Puedes cambiar MySQL por otro DB sin tocar dominio



Esto es hexagonal de verdad.



🧠 Regla de oro (Arquitectura Hexagonal) 



El adapter: --> Se crea para generar código limpio y se encarga de enviar o recibir información de la bd



Implementa el puerto

Orquesta persistencia



El mapper:



Traduce Dominio ↔ Infraestructura

NO contiene lógica de negocio







✅ Verificación del PASO 5



1️⃣ Ejecuta la app

2️⃣ Verifica que se creen tablas:

users

user\_roles

3️⃣ No debe haber errores





🔐 PASO 6 — Password Encryption (BCrypt)



Objetivo:

Implementar EncriptrarClaveService en infraestructura, usando Spring Security, sin contaminar dominio ni application.



🎯 Qué logramos en este paso



✔️ Implementación real de cifrado

✔️ Uso de BCrypt (estándar de la industria)

✔️ Dominio sigue desacoplado

✔️ Application ya puede registrar usuarios reales



📁 6.1 Estructura de paquetes



infrastructure

├── security

│   └── encryption

│       └── BCryptPasswordEncryptionAdapter.java

└── config

&nbsp;   └── SecurityBeansConfig.java







🔌 6.2 Adapter — BCryptPasswordEncryptionAdapter



📍 infrastructure/security/encryption/BCryptPasswordEncryptionAdapter.java



📌 Observa:



Implementa puerto del dominio

No hay lógica de negocio

BCrypt queda encapsulado





🧰 6.3 Bean de BCryptPasswordEncoder



Spring no crea este bean solo, debemos definirlo.

📍 infrastructure/config/SecurityBeansConfig.java



📌 Esto permite:



Inyectarlo donde sea necesario

Cambiar algoritmo sin tocar dominio ni application





✅ Verificación del PASO 6



Haz estas comprobaciones:



1️⃣ El proyecto compila

2️⃣ La app arranca sin errores

3️⃣ No hay warnings raros de seguridad

4️⃣ No se importó Spring en dominio







📧 PASO 7 — Confirmación de Email con Token



En este paso vamos a implementar todo el flujo de activación:



Crear token de verificación

Guardarlo en BD

Enviar link por email

Confirmar usuario

Habilitar acceso

Todo sin romper la arquitectura hexagonal.





🧱 7.1 Dominio — Token de verificación



📍 domain/model/token/EmailVerificationToken.java



📌 Dominio puro:



No JPA

No Spring

Lógica de negocio incluida (isExpired())





🔌 7.2 Puerto de dominio — TokenRepository



📍 domain/port/out/VerificationTokenRepositoryPort.java





🗄️ 7.3 Infraestructura — Entity JPA



📍 infrastructure/persistence/entity/VerificationTokenEntity.java





🗄️ 7.4 JPA Repository



📍 infrastructure/persistence/repository/VerificationTokenJpaRepository.java





🔁 7.5 Mapper Token



📍 infrastructure/persistence/mapper/VerificationTokenMapper.java





🔌 7.6 Adapter — TokenRepositoryAdapter



📍 infrastructure/persistence/adapter/VerificationTokenRepositoryAdapter.java





🧠 7.7 Application — ConfirmarUsuarioService



📍 application/usecase/user/ConfirmarUsuarioService.java





🔄 7.8 Actualizar RegisterUserService



Añade la creación del token. Se le añade la configuracipon del token



🧠 Por qué esto es correcto



✔️ Infrastructure implementa el puerto

✔️ Tiene @Component → Spring lo detecta

✔️ Dominio no sabe cómo se envía el email

✔️ Más adelante puedes cambiar a:



SMTP



SendGrid



Amazon SES



Gmail API



Sin tocar application ni dominio.





✉️ 7.8 Implementación del Adapter



📍 infrastructure/adapter/out/email/EmailSenderAdapter.java





🚀 PASO 8 — AuthController (REST API)



Exponer endpoints REST para:



📌 Registrar usuario

📌 Confirmar cuenta vía token

❌ SIN lógica de negocio

❌ SIN JPA

❌ SIN detalles de infraestructura



El controller solo delega a casos de uso (application).





📁 8.1 Estructura de paquetes



com.financeapp.infrastructure

└── adapter

&nbsp;   └── in

&nbsp;       └── web

&nbsp;           └── auth

&nbsp;               ├── AuthController.java

&nbsp;               └── dto

&nbsp;                   ├── RegisterUserHttpRequest.java

&nbsp;                   └── ApiResponse.java



Nota:

👉 DTOs HTTP NO son los mismos que los DTOs de application (muy importante).



📦 8.2 DTO HTTP — Registro



📍 infrastructure/adapter/in/web/auth/dto/RegisterUserHttpRequest.java





📦 8.3 DTO genérico de respuesta



📍 ApiResponse.java





🧠 8.4 Use Cases que vamos a usar



Desde application:



RegistrarUsuarioService

ConfirmarUsuarioService (lo implementamos en paso 7)





🌐 8.5 AuthController



📍 infrastructure/adapter/in/web/auth/AuthController.java



🧠 Por qué este controller está BIEN hecho

✔️ Controller = adaptador de entrada



Traduce HTTP → application

No decide reglas

No maneja transacciones



✔️ DTOs separados



HTTP DTO ≠ Application DTO

Cambiar API no rompe dominio



✔️ Fácil de testear



Mock de use cases

Tests de integración simples





🧪 8.6. Implementación de UseCaseConfig



📍 infrastructure/config/UseCaseConfig.java



🧠 Qué está pasando aquí



✔️ Spring crea el bean

✔️ Spring inyecta los puertos

✔️ Application sigue sin Spring

✔️ Infrastructure conecta todo



Esto es hexagonal real, no “hexagonal de YouTube”.







🧪 Pruebas rápidas (Postman / curl)



**Registro**



POST /api/auth/registro

Content-Type: application/json



{

&nbsp; "name": "Felipe",

&nbsp; "email": "felipe@test.com",

&nbsp; "password": "123456"

}



**Confirmación**

GET /api/auth/confirm?token=XXXX



\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*

Al momento de hacer pruebas, no nos dejará hacerlas porque al implementar Spring Security, este bloque las peticiones POST, etc. Implementamos una solución temporal por que luego usremos JWT



package com.estudiospringboot.finanzasapp.infrastructure.config;



import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.web.SecurityFilterChain;



@Configuration

public class SecurityConfig {



&nbsp;   @Bean

&nbsp;   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {



&nbsp;       http

&nbsp;           // Desactivar CSRF para APIs REST

&nbsp;           .csrf(csrf -> csrf.disable())



&nbsp;           // Autorización de endpoints

&nbsp;           .authorizeHttpRequests(auth -> auth

&nbsp;               .requestMatchers(

&nbsp;                       "/api/auth/\*\*",

&nbsp;                       "/error"

&nbsp;               ).permitAll()

&nbsp;               .anyRequest().authenticated()

&nbsp;           );



&nbsp;       return http.build();

&nbsp;   }

}



\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*













