package com.iteam.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.iteam.core.service.AuthSnapshot;
import com.iteam.core.service.UsersDAO;
import com.iteam.core.utils.BCrypt;
import com.iteam.domain.exception.PasswordMismatchException;
import com.iteam.domain.exception.UserNotFoundException;
import com.iteam.model.User;
import com.iteam.rest.request.LogInUserRequest;
import com.iteam.rest.response.LogInResponse;

@Controller
public class LogInController {

    @Autowired
    private UsersDAO dao;
    @Autowired
    private AuthSnapshot snapshot;

    @ResponseBody
    @RequestMapping(value = "users/login", method = RequestMethod.POST)
    public LogInResponse registerUser(@RequestBody LogInUserRequest request) {
        User user = dao.getUser(request.getUsername());
        if (user==null) {
            throw new UserNotFoundException();
        }
        boolean match = BCrypt.checkpw(request.getPassword(),
                user.getPassword());
        if (match) {
            String token = snapshot.addEntry(user.getUsername());
            return new LogInResponse(token);
        } else {
            throw new PasswordMismatchException();
        }
    }

}
