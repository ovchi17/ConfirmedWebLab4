package aca98b.web4l.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import aca98b.web4l.model.response.AuthResponse;
import aca98b.web4l.model.response.Response;
import aca98b.web4l.model.UserEntity;
import aca98b.web4l.service.implementation.UserServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {
    private final UserServiceImplementation userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody UserEntity userEntity) throws IOException {
        if(userService.register(userEntity)){
            userService.verify(userEntity);
            return ResponseEntity.ok(
                        AuthResponse.builder()
                                .timeStamp(LocalDateTime.now())
                                .message("user registered")
                                .status(HttpStatus.OK)
                                .statusCode(HttpStatus.OK.value())
                                .sessionId("test")
                                .sessionIdNonExpired(true)
                                .build()
                );
        } else {
            return ResponseEntity.badRequest().body(
                    AuthResponse.builder()
                            .timeStamp(LocalDateTime.now())
                            .message("username taken")
                            .status(HttpStatus.CONFLICT)
                            .statusCode(HttpStatus.CONFLICT.value())
                            .sessionId("test")
                            .sessionIdNonExpired(false)
                            .build()

            );
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody UserEntity userEntity){
        if(userService.verify(userEntity)){
            return ResponseEntity.ok(
            AuthResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .message("user logged in")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .sessionId("test")
                    .sessionIdNonExpired(true)
                    .build()
            );
        } else {
            return ResponseEntity.badRequest().body(
            AuthResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .message("incorrect password or login")
                    .status(HttpStatus.UNAUTHORIZED)
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .sessionId(null)
                    .sessionIdNonExpired(false)
                    .build()
            );
        }
    }

    @DeleteMapping("/logout")
    public ResponseEntity<AuthResponse> logout(@RequestBody UserEntity userEntity) {
        if(userService.logout(userEntity)){
            return ResponseEntity.ok(
                    AuthResponse.builder()
                            .timeStamp(LocalDateTime.now())
                            .message("user logged out")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .sessionId("test")
                            .sessionIdNonExpired(false)
                            .build()
            );
        } else {
            return ResponseEntity.badRequest().body(
                    AuthResponse.builder()
                            .timeStamp(LocalDateTime.now())
                            .message("non authorised or session expired")
                            .status(HttpStatus.UNAUTHORIZED)
                            .statusCode(HttpStatus.UNAUTHORIZED.value())
                            .sessionId("test")
                            .sessionIdNonExpired(false)
                            .build()
            );
        }
    }
}
