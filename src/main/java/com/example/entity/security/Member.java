package com.example.entity.security;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @项目：test
 * @创建者：linmin
 * @创建时间：2018/1/19
 * @公司：汽车易生活
 */
@Entity
public class Member implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String memberName;

    private String password;

    private String salt;

    private Byte status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="SysMemRole",joinColumns = {@JoinColumn(name="mid")},inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roleList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }
}
