package com.tulip.girlTest.shiro.config;

import com.tulip.girlTest.shiro.entity.SysPermission;
import com.tulip.girlTest.shiro.entity.SysRole;
import com.tulip.girlTest.shiro.entity.UserInfo;
import com.tulip.girlTest.shiro.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MyTestRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoService userInfoService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.print("***授权***获得角色及权限*****");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
         //从数据库取得各种角色及权限
        UserInfo userInfo = (UserInfo)principalCollection.getPrimaryPrincipal();
        for(SysRole role:userInfo.getRoleList()){
            authorizationInfo.addRole(role.getRole());
            for(SysPermission p:role.getPermissions()){
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return  authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
            System.out.print("***验证*************");
            String username = (String)authenticationToken.getPrincipal();
            System.out.println("**********getCredentials*******"+authenticationToken.getCredentials());

            UserInfo userInfo = userInfoService.findByUsername(username);
            System.out.println("----->>userInfo="+userInfo);
            if(userInfo == null){
                return null;
            }
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
              //传入输入的userinfo等去进行验证
                    userInfo.getName(), //用户名
                    userInfo.getPassword(), //密码
                    ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
                    getName()
            );
        return authenticationInfo;
    }
}