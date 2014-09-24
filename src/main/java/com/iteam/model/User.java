package com.iteam.model;

import java.util.ArrayList;
import org.springframework.data.mongodb.core.mapping.Document;
import com.google.common.collect.Lists;

@Document(collection = "users")
public class User {

    private String username;
    private String nickname;
    private String password;
    private ArrayList<Integer> goals = Lists.newArrayList();
    private ArrayList<Integer> matches = Lists.newArrayList();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Integer> getGoals() {
        return goals;
    }

    public void setGoals(ArrayList<Integer> goals) {
        this.goals = goals;
    }

    public ArrayList<Integer> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<Integer> matches) {
        this.matches = matches;
    }

}