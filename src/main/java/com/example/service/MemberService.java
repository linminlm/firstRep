package com.example.service;

import com.example.entity.security.Member;

/**
 * @项目：test
 * @创建者：linmin
 * @创建时间：2018/2/2
 * @公司：汽车易生活
 */
public interface MemberService {
    Member findByMembername(String memberName);
}
