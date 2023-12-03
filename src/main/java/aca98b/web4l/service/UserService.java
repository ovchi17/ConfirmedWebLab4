package aca98b.web4l.service;


import aca98b.web4l.model.UserEntity;

public interface UserService{
    boolean verify(UserEntity userEntity);
    boolean register(UserEntity userEntity);
    boolean logout(UserEntity userEntity);
    void delete(String login);
} 