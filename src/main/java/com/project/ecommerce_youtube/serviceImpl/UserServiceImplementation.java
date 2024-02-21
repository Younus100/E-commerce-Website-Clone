package com.project.ecommerce_youtube.service;


import com.project.ecommerce_youtube.Reository.UserRepository;
import com.project.ecommerce_youtube.config.JwtProvider;
import com.project.ecommerce_youtube.exception.UserException;
import com.project.ecommerce_youtube.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImplementation implements UserService {
    private UserRepository userRepository;
    private JwtProvider  jwtProvider;

    public UserServiceImplementation(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public User findUserById(Long userid) throws UserException {
        Optional<User> user = userRepository.findById(userid);
        if(!user.isPresent()) {  throw  new UserException("User not found by userId"+userid); }
        return user.get();
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getemailFromToken(jwt);
        User user = userRepository.findByEmail(email);
        if(user==null) {  throw  new UserException("User not found by Email"+email); }
        return user;
    }

    @Override
    public Long findUserByEmail(String email) throws UserException {
        return  userRepository.findUserIdByEmail(email);
    }
}
