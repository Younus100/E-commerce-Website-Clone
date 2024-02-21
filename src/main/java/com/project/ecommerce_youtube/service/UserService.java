package com.project.ecommerce_youtube.service;


import com.project.ecommerce_youtube.exception.UserException;
import com.project.ecommerce_youtube.model.User;
import jdk.jshell.spi.ExecutionControl;


public interface UserService {

    public User findUserById(Long userid) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;

    public Long findUserByEmail(String email) throws UserException;

}
