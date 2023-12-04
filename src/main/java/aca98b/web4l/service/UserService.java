package aca98b.web4l.service;


import aca98b.web4l.model.entities.User;

public interface UserService{
    boolean verify(User user);
    boolean register(User user);
    boolean logout(User user);
    void delete(String login);
} 