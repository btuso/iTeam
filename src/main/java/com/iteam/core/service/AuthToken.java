package com.iteam.core.service;

import com.iteam.core.utils.BCrypt;

public class AuthToken {

    public static final String DISPOSABLE = "disposable";
    public static final String TOKEN = "token";

    private String token;
    private String username;
    private boolean disposable;

    public AuthToken(String user) {
        this.username = user;
        token = createToken(user);
        this.keepAlive();
    }

    private String createToken(String username) {
        char[] array = getSmallMillisArray();
        int length = array.length-1;
        String moduser = modifyUser(username, array, length);
        Integer rounds = getGensaltRounds(array, length);
        return BCrypt.hashpw(moduser, BCrypt.gensalt(rounds));
    }

    private char[] getSmallMillisArray() {
        long currentTimeMillis = System.currentTimeMillis();
        String millis = String.valueOf(currentTimeMillis);
        millis = millis.substring(millis.length()-3);
        return millis.toCharArray();
    }

    private String modifyUser(String user, char[] array, int length) {
        StringBuilder builder = new StringBuilder();
        builder.append(array[length-2]);
        builder.append(user);
        builder.append(array[length]);
        return builder.toString();
    }

    private Integer getGensaltRounds(char[] array, int length) {
        int rounds = Character.getNumericValue(array[length-1]);
        rounds = rounds<4 ? rounds += 4 : rounds;
        return rounds;
    }

    public String getToken() {
        return token;
    }

    public String getUser() {
        return username;
    }

    public void keepAlive() {
        disposable = false;
    }

    public void markAsDisposable() {
        disposable = true;
    }

    public boolean isDisposable() {
        return disposable;
    }
}
