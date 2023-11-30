package aca98b.web4l.controller;

import aca98b.web4l.config.AppConfig;
import aca98b.web4l.model.entity.UserEntity;
import aca98b.web4l.model.entity.repository.UserRepository;
import aca98b.web4l.model.request.UserRequest;
import aca98b.web4l.model.response.AuthSessionResponse;
import aca98b.web4l.session.SessionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
@RequestMapping
public class AuthController {
    private final AuthenticationManager manager;
    private final SessionHandler handler;
    private final UserRepository repository;
    private final AppConfig config;

    @Autowired
    public AuthController(final AuthenticationManager manager,
                          final SessionHandler handler,
                          final UserRepository repository,
                          final AppConfig config) {
        this.manager = manager;
        this.handler = handler;
        this.repository = repository;
        this.config = config;
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<AuthSessionResponse> login(@RequestBody final UserRequest request) {
        manager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()
        ));
        final String sessionID = handler.register(request.getUsername());
        final AuthSessionResponse response = new AuthSessionResponse();
        response.setSessionID(sessionID);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader (HttpHeaders.AUTHORIZATION) String auth) {
        handler.invalidate(auth);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<AuthSessionResponse> register (@RequestBody final UserRequest request) {
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(config.passwordEncoder().encode(request.getPassword()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        try {
            repository.save(user);
        } catch (Throwable e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Cannot register user with that username (" + request.getUsername() + ") " +
                            "( / Maybe it already exists).");
        }
        return login(request);
    }


}
