package com.coderhouse.ecommerce.service.impl;

import com.coderhouse.ecommerce.builder.UserBuilder;
import com.coderhouse.ecommerce.exception.LoginErrorException;
import com.coderhouse.ecommerce.exception.UserAlreadyExistException;
import com.coderhouse.ecommerce.model.request.UserLogin;
import com.coderhouse.ecommerce.model.request.UserRegister;
import com.coderhouse.ecommerce.model.response.UserResponse;
import com.coderhouse.ecommerce.repository.UserRepository;
import com.coderhouse.ecommerce.service.UserService;
import com.coderhouse.ecommerce.util.CheckExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private CheckExist checkExist;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserResponse register(UserRegister request) throws Exception {
        if(checkExist.user(request.getEmail())) {
            throw new UserAlreadyExistException();
        }
        //encriptando la contraseña
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        var document = repository.save(UserBuilder.requestRegisterToDocument(request));
        return UserBuilder.documentToResponse(document);
    }

    @Override
    public UserResponse login(UserLogin request) throws Exception {
        var userEmail = request.getEmail();
        if(!checkExist.user(userEmail) ||
                !passwordEncoder.matches(request.getPassword(), getPasswordByEmail(userEmail))) {
            throw new LoginErrorException();
        }
        return UserBuilder.requestLoginToResponse(request);
    }

    private String getPasswordByEmail(String email) {
        return repository.findByEmail(email).getPassword();
    }
}
