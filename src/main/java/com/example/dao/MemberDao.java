package com.example.dao;

import com.example.entity.security.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @项目：test
 * @创建者：linmin
 * @创建时间：2018/2/2
 * @公司：汽车易生活
 */
public interface MemberDao extends JpaRepository<Member,Integer> {
    Member findByMemberName(String memberName);
}
