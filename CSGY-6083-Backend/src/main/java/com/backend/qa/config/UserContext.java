package com.backend.qa.config;

import com.backend.qa.domain.User;

public class UserContext {
    private static ThreadLocal<String> userHolder = new ThreadLocal<String>();

    public static void setUser(String username) {
        userHolder.set(username);
    }

    public static String getUser() {
        return userHolder.get();
    }

    public static void removeUser() {
        userHolder.remove();
    }
}
