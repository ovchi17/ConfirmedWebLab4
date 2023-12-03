package aca98b.web4l.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.time.format.DateTimeFormatter;

import aca98b.web4l.model.response.Response;
import aca98b.web4l.model.UserEntity;
import aca98b.web4l.service.implementation.ElementServiceImplementation;
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
    public ResponseEntity<Response> registerUser(@RequestBody UserEntity userEntity) throws IOException {
        if(userService.register(userEntity)){
            userService.verify(userEntity);
            return ResponseEntity.ok(
                        Response.builder()
                                .timeStamp(LocalDateTime.now())
                                .data(Map.of("logged in", true))
                                .message("user logged in")
                                .status(HttpStatus.OK)
                                .statusCode(HttpStatus.OK.value())
                                .build()
                );
//            return ResponseEntity.ok(
//                Response.builder()
//                        .timeStamp(LocalDateTime.now())
//                        .data(Map.of("user", true))
//                        .message("user registred")
//                        .status(HttpStatus.CREATED)
//                        .statusCode(HttpStatus.CREATED.value())
//                        .build()
//
//                );
        } else {
            return ResponseEntity.badRequest().body(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("user", false))
                        .message("username taken")
                        .status(HttpStatus.CONFLICT)
                        .statusCode(HttpStatus.CONFLICT.value())
                        .build()

                );
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody UserEntity userEntity){
        if(userService.verify(userEntity)){
            return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("logged in", true))
                    .message("user logged in")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build()
            );
        } else {
            return ResponseEntity.badRequest().body(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("logged in", false))
                    .message("incorrect password or login")
                    .status(HttpStatus.UNAUTHORIZED)
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .build()
            );
        }
    }

    @DeleteMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody UserEntity userEntity) {
        if(userService.logout(userEntity)){
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .data(Map.of("logged out", true))
                            .message("user logged put")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .build()
            );
        } else {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .data(Map.of("logged in", false))
                            .message("incorrect password or login")
                            .status(HttpStatus.UNAUTHORIZED)
                            .statusCode(HttpStatus.UNAUTHORIZED.value())
                            .build()
            );
        }
    }
}
