package com.example.dao;

import com.example.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @项目：test
 * @创建者：linmin
 * @创建时间：2017/12/27
 * @公司：汽车易生活
 */
public interface UserDao extends JpaRepository<User,Integer> {
}
