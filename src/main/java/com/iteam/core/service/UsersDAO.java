package com.iteam.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.iteam.core.utils.BCrypt;
import com.iteam.domain.exception.UsernameInUseException;
import com.iteam.model.User;
import com.iteam.rest.request.RegisterUserRequest;

@Repository
public class UsersDAO {

    private static final String USERNAME = "username";
    @Autowired
    private MongoTemplate mongoTemplate;

    public void add(RegisterUserRequest request) {
        checkIfUsernameIsInUse(request.getUsername());
        User user = createUser(request);
        mongoTemplate.insert(user);
    }

    private void checkIfUsernameIsInUse(String username) {
        Query query = new Query(Criteria.where(USERNAME).is(username));
        User user = mongoTemplate.findOne(query, User.class);
        if (user!=null) {
            throw new UsernameInUseException();
        }
    }

    private User createUser(RegisterUserRequest request) {
        User user = new User();
        user.setNickname(request.getNickname());
        user.setUsername(request.getUsername());
        String password = hashPassword(request.getPassword());
        user.setPassword(password);
        return user;
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public User getUser(String username) {
        Query query = new Query(Criteria.where(USERNAME).is(username));
        User user = mongoTemplate.findOne(query, User.class);
        return user;
    }

}
