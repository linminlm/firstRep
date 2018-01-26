package com.example.dao;

import com.example.entity.user.PubUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PubUserDao extends JpaRepository<PubUser,Long> {
    PubUser findByUserName(String userName);
}
