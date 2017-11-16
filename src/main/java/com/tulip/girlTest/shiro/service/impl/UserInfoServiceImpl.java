package com.tulip.girlTest.shiro.service.impl;

import com.tulip.girlTest.shiro.dao.UserInfoDao;
import com.tulip.girlTest.shiro.entity.UserInfo;
import com.tulip.girlTest.shiro.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("********UserInfoServiceImpl.findByUsername()********");
        return userInfoDao.findByUsername(username);
    }
}