package com.example.service;

import com.example.entity.user.PubUser;

/**
 * @author linmin
 */
public interface PubUserService {

    PubUser addPubUser(PubUser pubUser) throws Exception;

    void activeEmail(String checkCode,String username);

    PubUser getOnePubUser(String userName);
}
