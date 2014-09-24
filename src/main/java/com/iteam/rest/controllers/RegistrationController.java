package com.iteam.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.iteam.core.service.UsersDAO;
import com.iteam.rest.request.RegisterUserRequest;

@Controller
@RequestMapping(value = "users")
public class RegistrationController {

    @Autowired
    private UsersDAO dao;

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public void registerUser(@RequestBody RegisterUserRequest request) {
        dao.add(request);
    }

}
