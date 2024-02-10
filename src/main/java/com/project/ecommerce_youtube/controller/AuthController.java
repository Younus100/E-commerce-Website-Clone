package com.project.ecommerce_youtube.controller;

import com.project.ecommerce_youtube.Reository.UserRepository;
import com.project.ecommerce_youtube.config.JwtProvider;
import com.project.ecommerce_youtube.exception.UserException;
import com.project.ecommerce_youtube.model.User;
import com.project.ecommerce_youtube.request.LoginRequest;
import com.project.ecommerce_youtube.response.AuthResponse;
import com.project.ecommerce_youtube.service.CustomerServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    public AuthController(JwtProvider jwtProvider, PasswordEncoder passwordEncoder, UserRepository userRepository, CustomerServiceImplementation customerServiceImplementation) {
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.customerServiceImplementation = customerServiceImplementation;
    }

    private JwtProvider  jwtProvider;

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    private CustomerServiceImplementation customerServiceImplementation;


    @PostMapping("/signup")
        public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user)throws UserException {
        String email = user.getEmail();
        String password = user.getPassword();
        String firstname =user.getFirstName();
        String lastname = user.getLastName();
        User isEmailExist = userRepository.findByEmail(email);

        if(isEmailExist!=null) {
            throw new UserException("Email Already Exist");
        }

        User createduser = new User();
        createduser.setEmail(email);
        createduser.setPassword(passwordEncoder.encode(password));
        createduser.setFirstName(firstname);
        createduser.setLastName(lastname);

        User savedUser = userRepository.save(createduser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMsg("SignUp Success");
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest)throws UserException {
        String  username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMsg("SignIn Success");
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customerServiceImplementation.loadUserByUsername(username);
        if(userDetails==null) {   throw  new BadCredentialsException("Invalid USerNAme");   }
        if(!passwordEncoder.matches(password,userDetails.getPassword())) {
            throw  new BadCredentialsException("Invalid Password...");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null);
    }

}
