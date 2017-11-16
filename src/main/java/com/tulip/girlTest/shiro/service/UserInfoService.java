package com.tulip.girlTest.shiro.service;

import com.tulip.girlTest.shiro.entity.UserInfo;

public interface UserInfoService {
    public UserInfo findByUsername(String username);
}