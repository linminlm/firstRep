package com.example.service.impl;

import com.example.dao.PubUserDao;
import com.example.entity.user.PubUser;
import com.example.service.PubUserService;
import com.example.util.Base64Utils;
import com.example.util.RSAUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;

@Component
public class PubUserServiceImpl implements PubUserService {

    @Autowired
    private PubUserDao pubUserDao;

    @Override
    public PubUser addPubUser(PubUser pubUser) throws Exception {
        Map<String, Object> keyMap = RSAUtil.genKeyPair();
        String userName = pubUser.getUserName();
        String publicKey = RSAUtil.getPublicKey(keyMap);
        String privateKey = RSAUtil.getPrivateKey(keyMap);
        String sign = RSAUtil.sign(userName.getBytes(), privateKey);
        String checkCode = Base64Utils.encode(userName.getBytes());
        String pwd = DigestUtils.md5Hex(pubUser.getPassword());

        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[15];
        random.nextBytes(bytes);
        String salt = Base64.encodeBase64String(bytes);

        pubUser.setCheckCode(checkCode);
        pubUser.setCreateTime(new Date().getTime());
        pubUser.setExpiryTime(new Date().getTime()+30*60*1000);
        pubUser.setStatus("00");
        pubUser.setPassword(pwd);
        pubUser.setPublicKey(publicKey);
        pubUser.setPrivateKey(privateKey);
        pubUser.setSign(sign);
        pubUser.setSalt(salt);

        PubUser user = pubUserDao.save(pubUser);
        return user;
    }
}
