package aca98b.web4l.service.implementation;

import aca98b.web4l.model.UserEntity;
import aca98b.web4l.repo.UserRepository;
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
    public boolean verify(UserEntity userEntity) {
        log.info("searching for user {}", userEntity.getUsername());
        if(userRepository.existsByUsername(userEntity.getUsername())){
            if(userRepository.existsByPassword(userEntity.getPassword())){
                log.info("logged in");
                return true;
            }
            log.info("incorrect password");
            return false;
        }
        log.info("user {}: not found", userEntity.getUsername());
        return false;
    }

    @Override
    public boolean register(UserEntity userEntity) {
        log.info("registering user");
        if(userRepository.existsByUsername(userEntity.getUsername())){
            log.info("user already exists");
            return false;
        }
        userRepository.save(userEntity);
        return true;
    }

    @Override
    public boolean logout (UserEntity userEntity) {
        log.info("logging out");
        if (userRepository.existsBySessionId(userEntity.getSessionId())) {
            if (userRepository.sessionNonExpired(userEntity.isSessionNonExpired())) {
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
    public void delete(String login) {
        log.info("deleting user {}", login);
        userRepository.deleteById(login);
    }
    
}
