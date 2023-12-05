package aca98b.web4l.service;

import aca98b.web4l.model.Role;
import aca98b.web4l.model.entities.User;
import aca98b.web4l.model.entities.repo.UserRepository;
import aca98b.web4l.model.request.AuthRequest;
import aca98b.web4l.model.response.AuthResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(AuthRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .jwt(jwtToken)
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = repository.findByUsername(request.getUsername()); //todo: implement exception
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .jwt(jwtToken)
                .build();
    }

//    public AuthResponse logout (AuthRequest request) {
//        //todo: not implemented yet
//    }

    public void delete(Long id) {
        log.info("deleting user {}", id);
        //todo: REWRITE
        repository.deleteById(id);
    }
    
}
