package com.backend.qa.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SessionManager {
    private HashMap<String, String> map = new HashMap<String, String>();

    public String get(String token){
        return map.getOrDefault(token, null);
    }

    public boolean set(String token, String name){
        if(map.get(token) != null){
            return false;
        }
        if(map.put(token, name) != null){
            return true;
        }
        return false;
    }

    public boolean delete(String token){
        if(map.remove(token) != null){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "SessionManager{" +
                "map=" + map +
                '}';
    }
}
