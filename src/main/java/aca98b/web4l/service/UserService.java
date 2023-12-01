package aca98b.web4l.service;


import aca98b.web4l.model.User;

public interface UserService{
    boolean verify(User user);
    boolean register(User user);
    void delete(String login);
} 