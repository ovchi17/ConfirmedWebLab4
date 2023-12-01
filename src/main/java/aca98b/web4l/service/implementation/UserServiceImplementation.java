package aca98b.web4l.service.implementation;

import aca98b.web4l.model.User;
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
    public boolean verify(User user) {
        log.info("searching for user {}", user.getLogin());
        if(userRepository.existsById(user.getLogin())){
            if(userRepository.existsByPassword(user.getPassword())){
                log.info("logged in");
                return true;
            }
            log.info("incorrect password");
            return false;
        }
        log.info("user {}: not found", user.getLogin());
        return false;
    }

    @Override
    public boolean register(User user) {
        log.info("registering user");
        if(userRepository.existsById(user.getLogin())){
            log.info("user already exists");
            return false;
        }
        userRepository.save(user);
        return true;
    }

    @Override
    public void delete(String login) {
        log.info("deleting user {}", login);
        userRepository.deleteByLogin(login);
    }
    
}
