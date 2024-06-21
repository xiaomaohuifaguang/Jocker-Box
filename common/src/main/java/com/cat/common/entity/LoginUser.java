package com.cat.common.entity;

import java.util.List;

/***
 * 登录用户信息
 * @title LoginUser
 * @description 登录用户信息
 * @author xiaomaohuifaguang
 * @create 2024/6/20 22:24
 **/
public class LoginUser {

    private String userId;
    private String username;
    private String nickname;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
