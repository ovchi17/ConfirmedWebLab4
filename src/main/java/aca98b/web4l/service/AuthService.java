package aca98b.web4l.service;

import aca98b.web4l.model.Role;
import aca98b.web4l.model.entities.User;
import aca98b.web4l.model.entities.repo.UserRepository;
import aca98b.web4l.model.request.AuthRequest;
import aca98b.web4l.model.response.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(AuthRequest request) {
        log.info("registering user...");
        if (repository.existsByUsername(request.getUsername())){
            log.info("user already exists");
            return AuthResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .message("Username taken.")
                    .status(HttpStatus.CONFLICT)
                    .statusCode(HttpStatus.CONFLICT.value())
                    .build();
        }
        else {
            User user = User.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();
            repository.save(user);

            var jwtToken = jwtService.generateToken(user);
            return AuthResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .message("User registered.")
                    .status(HttpStatus.CREATED)
                    .statusCode(HttpStatus.CREATED.value())
                    .jwt(jwtToken)
                    .build();
        }
    }

    public AuthResponse authenticate(AuthRequest request) {
        log.info("searching for user {}", request.getUsername());
        if(repository.existsByUsername(request.getUsername())){
            if (passwordEncoder.matches(request.getPassword(), repository.findByUsername(request.getUsername()).getPassword())) {
                log.info("logged in");
                var user = repository.findByUsername(request.getUsername());
                var jwtToken = jwtService.generateToken(user);
                return AuthResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("User logged in.")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .jwt(jwtToken)
                        .build();
            }
            log.info("incorrect password");
            return AuthResponse.builder()
                            .timeStamp(LocalDateTime.now())
                            .message("Incorrect password.")
                            .status(HttpStatus.UNAUTHORIZED)
                            .statusCode(HttpStatus.UNAUTHORIZED.value())
                            .build();
        }
        log.info("user {}: not found", request.getUsername());
        return AuthResponse.builder()
                .timeStamp(LocalDateTime.now())
                .message("Incorrect username.")
                .status(HttpStatus.UNAUTHORIZED)
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .build();
    }

    //todo: DO LOGOUT FUNCTION TO REMOVE JWT
}