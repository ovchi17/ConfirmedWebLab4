package aca98b.web4l.service.implementation;

import aca98b.web4l.model.entities.User;
import aca98b.web4l.model.repo.UserRepository;
import aca98b.web4l.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;

    @Override
    public boolean verify(User user) {
        log.info("searching for user {}", user.getUsername());
        if(userRepository.existsByUsername(user.getUsername())){
            if(userRepository.existsByPassword(user.getPassword())){
                log.info("logged in");
                return true;
            }
            log.info("incorrect password");
            return false;
        }
        log.info("user {}: not found", user.getUsername());
        return false;
    }

    @Override
    public boolean register(User user) {
        log.info("registering user");
        if(userRepository.existsByUsername(user.getUsername())){
            log.info("user already exists");
            return false;
        }
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean logout (User user) {
        log.info("logging out");
        if (userRepository.existsBySessionId(user.getSessionId())) {
            if (userRepository.sessionNonExpired(user.isSessionNonExpired())) {
                log.info("logged out");
                return true;
            }
            log.info("session expired");
            return false;
        }
        log.info("non authorised");
        return false;
    }


    @Override
    public void delete(Long id) {
        log.info("deleting user {}", id);
        userRepository.deleteById(id);
    }
    
}
