package aca98b.web4l.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import aca98b.web4l.model.request.AuthRequest;
import aca98b.web4l.model.response.AuthResponse;
import aca98b.web4l.model.entities.User;
import aca98b.web4l.service.implementation.UserServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserServiceImplementation userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) throws IOException {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request){
        return ResponseEntity.ok(userService.authenticate(request));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<AuthResponse> logout(@RequestBody AuthRequest request) {
        //todo: not implemented yet
    }
}
