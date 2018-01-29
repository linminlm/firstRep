package com.example.service.impl;

import com.example.Enum.UserEnum;
import com.example.dao.PubUserDao;
import com.example.entity.user.PubUser;
import com.example.exception.UserException;
import com.example.service.PubUserService;
import com.example.util.Base64Utils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Date;
import java.util.UUID;

@Component
public class PubUserServiceImpl implements PubUserService {

    @Autowired
    private PubUserDao pubUserDao;

    @Override
    public PubUser addPubUser(PubUser pubUser) throws Exception {
        String userName = pubUser.getUserName();
        String privatekey = UUID.randomUUID().toString().replace("-","");
        String publicKey = DigestUtils.md5Hex(privatekey);
        String checkCode = Base64Utils.encode(userName.getBytes());

        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[15];
        random.nextBytes(bytes);
        String salt = Base64.encodeBase64String(bytes);
        String pwd = DigestUtils.md5Hex(pubUser.getPassword()+salt);
        pubUser.setCheckCode(checkCode);
        pubUser.setCreateTime(new Date().getTime());
        pubUser.setExpiryTime(new Date().getTime()+30*60*1000);
        pubUser.setStatus("00");
        pubUser.setPassword(pwd);
        pubUser.setPrivateKey(privatekey);
        pubUser.setPublicKey(publicKey);
        pubUser.setSalt(salt);
        PubUser user = pubUserDao.save(pubUser);
        return user;
    }

    @Override
    public void activeEmail(String checkCode, String username) {
        PubUser pubUser = pubUserDao.findByUserName(username);
        long nowTime = new Date().getTime();
        if(pubUser == null || !(pubUser.getCheckCode().equals(checkCode)) || pubUser.getExpiryTime()<nowTime){

            throw new UserException(UserEnum.UNKNOWN_FAIL);
        }
        pubUser.setStatus("01");
        pubUserDao.save(pubUser);
    }

    @Override
    public PubUser getOnePubUser(String userName) {
        return pubUserDao.findByUserName(userName);
    }
}
