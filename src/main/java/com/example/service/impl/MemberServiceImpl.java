package com.example.service.impl;

import com.example.dao.MemberDao;
import com.example.entity.security.Member;
import com.example.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @项目：test
 * @创建者：linmin
 * @创建时间：2018/2/2
 * @公司：汽车易生活
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public Member findByMembername(String memberName) {
        return memberDao.findByMemberName(memberName);
    }
}
