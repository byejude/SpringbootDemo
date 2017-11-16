package com.tulip.girlTest.shiro.config;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    //注入自定义realm
    @Bean
    public MyTestRealm MyTestRealm(){
        MyTestRealm myTestRealm = new MyTestRealm();
        myTestRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myTestRealm;
    }

    //自定义密码匹配器
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    //注入securityManager
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(MyTestRealm());
        return securityManager;
    }

   //定义过滤器链
    @Bean
    public ShiroFilterFactoryBean configShiroFilter(SecurityManager securityManager){
        System.out.print("*****configShiroFilte******");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //filter
        Map<String,String> filterChainDenfinitMap = new LinkedHashMap<String,String>();
        filterChainDenfinitMap.put("/logout","logout");
        filterChainDenfinitMap.put("/**","authc");

        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDenfinitMap);
        return shiroFilterFactoryBean;


    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}