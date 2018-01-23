package com.example.service;

/**
 * @项目：test
 * @创建者：linmin
 * @创建时间：2018/1/17
 * @公司：汽车易生活
 */
public interface MailService {
    void sendSimpleMail(String to, String subject, String content);
}
