package com.iteam.core.service;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.google.common.collect.Maps;

@Component
public class AuthSnapshot {

    private static final int TOKEN_LIFE_SPAN = 2*24*60*60*1000;// two days
    private Map<String, AuthToken> tokenSnapshot = Maps.newHashMap();

    public String addEntry(String username) {
        AuthToken token = new AuthToken(username);
        tokenSnapshot.put(token.getToken(), token);
        return token.getToken();
    }

    public String getUsernameForToken(String token) {
        AuthToken authToken = tokenSnapshot.get(token);
        return authToken!=null ? authToken.getUser() : null;
    }

    public void removeEntry(String token) {
        tokenSnapshot.remove(token);
    }

    @Scheduled(fixedRate = TOKEN_LIFE_SPAN)
    private void pruneTokens() {
        Set<Entry<String, AuthToken>> entrySet = tokenSnapshot.entrySet();
        Iterator<Entry<String, AuthToken>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Entry<String, AuthToken> entry = iterator.next();
            AuthToken authToken = entry.getValue();
            if (authToken.isDisposable()) {
                iterator.remove();
            } else {
                authToken.markAsDisposable();
            }
        }
    }

}
