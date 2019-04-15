package com.example.service;

/**
 * @项目：test
 * @author ：linmin
 * @创建时间：2018/1/17
 */
public interface MailService {
    void sendSimpleMail(String to, String subject, String content);
}
