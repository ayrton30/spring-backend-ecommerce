package com.coderhouse.ecommerce.service.impl;

import com.coderhouse.ecommerce.builder.UserBuilder;
import com.coderhouse.ecommerce.exception.LoginErrorException;
import com.coderhouse.ecommerce.exception.NotEqualPasswordsException;
import com.coderhouse.ecommerce.exception.UserAlreadyExistException;
import com.coderhouse.ecommerce.model.request.UserLogin;
import com.coderhouse.ecommerce.model.request.UserRegister;
import com.coderhouse.ecommerce.model.response.UserResponse;
import com.coderhouse.ecommerce.repository.UserRepository;
import com.coderhouse.ecommerce.service.UserService;
import com.coderhouse.ecommerce.util.CheckExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private CheckExist checkExist;


    @Override
    public UserResponse register(UserRegister request) throws UserAlreadyExistException, NotEqualPasswordsException {
        if(!request.getPassword().equals(request.getRepeatPassword())) {
            throw new NotEqualPasswordsException();
        }

        if(checkExist.user(request.getEmail())) {
            throw new UserAlreadyExistException();
        }

        var document = repository.save(UserBuilder.requestRegisterToDocument(request));
        return UserBuilder.documentToResponse(document);
    }

    @Override
    public UserResponse login(UserLogin request) throws LoginErrorException {
        var userEmail = request.getEmail();
        if(checkExist.user(userEmail)) {
            var passRequest = request.getPassword();
            var passUser = repository.findByEmail(userEmail).getPassword();

            if(!passRequest.equals(passUser)){
                throw new LoginErrorException();
            }
            return UserBuilder.requestLoginToResponse(request);
        }
        throw new LoginErrorException();
    }
}
