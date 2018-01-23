package com.example.service.impl;

import com.example.Enum.UserEnum;
import com.example.dao.UserDao;
import com.example.entity.user.User;
import com.example.exception.UserException;
import com.example.mapper.UserDaoMapper;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @项目：test
 * @创建者：linmin
 * @创建时间：2017/12/27
 * @公司：汽车易生活
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDaoMapper userDaoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<User> userLists() {
        try {
            //return userDao.findAll();
            return userDaoMapper.findAll();
        } catch (Exception e) {
            throw new UserException(UserEnum.UNKNOWN_FAIL);
        }
    }

    @Override
    public User getOneUser(Integer id) {
        try {
            return userDaoMapper.findOneById(id);
        } catch (Exception e) {
            throw new UserException(UserEnum.UNKNOWN_FAIL);
        }
    }

    @Override
    public User addUser(User user) {
        try {
            User save = userDao.save(user);
            return save;
        } catch (Exception e) {
            throw new UserException(UserEnum.ADD_FAIL);
        }
    }

    @Override
    public void delUser(Integer id) {
        try {
            userDao.delete(id);
        } catch (Exception e) {
            throw new UserException(UserEnum.DEL_FAIL);
        }
    }

    @Override
    public User updUser(User user) {
        try {
            User save = userDao.save(user);
            return save;
        } catch (Exception e) {
            throw new UserException(UserEnum.UPD_FAIL);
        }
    }
}
