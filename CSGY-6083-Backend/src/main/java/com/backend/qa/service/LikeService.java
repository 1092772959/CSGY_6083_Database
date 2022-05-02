package com.backend.qa.service;

import com.backend.qa.dao.LikeDao;
import com.backend.qa.domain.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LikeService {
    @Autowired
    public LikeDao likeDao;

    public ArrayList<Like> getLikeByAnsId(int id){
        return likeDao.getLikeByAnsId(id);
    }

    public ArrayList<Like> getAllLikes() {
        return likeDao.getAllLikes();
    }

    public ArrayList<Like> getLikeByUsername(String username) {
        return likeDao.getAllLikesByUsername(username);
    }
}
