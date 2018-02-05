package com.example.config;

import com.example.entity.security.Member;
import com.example.entity.security.SysPermission;
import com.example.entity.security.SysRole;
import com.example.service.MemberService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * @项目：test
 * @创建者：linmin
 * @创建时间：2018/2/2
 * @公司：汽车易生活
 */
public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private MemberService memberService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Member member  = (Member)principals.getPrimaryPrincipal();
        for(SysRole role:member.getRoleList()){
            authorizationInfo.addRole(role.getRole());
            for(SysPermission p:role.getPermissions()){
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;//获取用户输入的token
        String username = utoken.getUsername();
        Member user = memberService.findByMembername(username);
        return new SimpleAuthenticationInfo(user, user.getPassword(),getName());//放入shiro.调用CredentialsMatcher检验密码
    }

}
