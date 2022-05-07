package com.backend.qa.service;

import com.backend.qa.dao.UserDao;
import com.backend.qa.domain.User;
import com.backend.qa.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private SessionManager sessionManager;

    public User getByuid(int uid){
        return userDao.getByUid(uid);
    }

    public ArrayList<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getByUsername(String username) {
        return userDao.getByUsername(username);
    }

    public boolean register(HttpServletResponse response, String username, String password, String email) {
        User oldUser = getByUsername(username);
        if(oldUser != null){
            return false;
        }

        try {
            userDao.insert(username, Md5.inputPass2DBPass(password), email);
            User user = userDao.getByUsername(username);
            if(user == null){
                return false;
            }
            // set token in Cookie
            String token = UUID.randomUUID().toString().replace("-","");
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(1000); // in seconds
            response.addCookie(cookie);
            sessionManager.set(token, username);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean login(HttpServletResponse response, String username, String password) {
        User user = getByUsername(username);
        if(user == null){
            return false;
        }
        if(!Md5.inputPass2DBPass(password).equals(user.getPassword())){
            return false;
        }
        String token = UUID.randomUUID().toString().replace("-","");
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(1000); // in seconds
        response.addCookie(cookie);
        sessionManager.set(token, username);
        return true;
    }

    public boolean logout(HttpServletResponse response, HttpServletRequest request) {
        String paramToken = request.getParameter("token");
        String cookieToken = null;
        Cookie[]  cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for(Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    cookieToken = cookie.getValue();
                }
            }
        }
        if(cookieToken == null && paramToken == null) {
            return false;
        }
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        sessionManager.delete(cookieToken != null ? cookieToken:paramToken);
        return true;
    }

    public boolean profile(String uid, String username, String password, String email, String profile, String state, String country, String city) {
        User oldUser = getByUsername(username);
        if(oldUser != null && !oldUser.getUid().equals(Integer.parseInt(uid))){
            return false;
        }
        return userDao.profile(uid, username, Md5.inputPass2DBPass(password), email, profile, state, country, city);
    }
}
