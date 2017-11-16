package com.tulip.girlTest.shiro.dao;

import com.tulip.girlTest.shiro.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoDao extends CrudRepository<UserInfo,Long> {
        public UserInfo findByUsername(String username);
}