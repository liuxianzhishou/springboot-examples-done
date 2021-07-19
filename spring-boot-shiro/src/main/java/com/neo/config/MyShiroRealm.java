package com.neo.config;

import com.neo.model.SysPermission;
import com.neo.model.SysRole;
import com.neo.model.UserInfo;
import com.neo.sevice.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * 在认证、授权内部实现机制中都有提到，最终处理都将交给Real进行处理。
 * 因为在 Shiro 中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
 * 通常情况下，在 Realm 中会直接从我们的数据源中获取 Shiro 需要的验证信息。
 * 可以说，Realm 是专用于安全框架的 DAO. Shiro 的认证过程最终会交由 Realm 执行，
 * 这时会调用 Realm 的getAuthenticationInfo(token)方法。
 */

/**
 * -------鏈接權限的實現--------------
 * Shiro 的权限授权是通过继承AuthorizingRealm抽象类，
 * 重载doGetAuthorizationInfo();
 * 当访问到页面的时候，链接配置了相应的权限或者 Shiro 标签才会执行此方法否则不会执行，
 * 所以如果只是简单的身份认证没有权限的控制的话，那么这个方法可以不进行实现，直接返回 null 即可。
 */

/**
 * --------登錄認證-------------
 * getAuthenticationInfo(token)
 * 该方法主要执行以下操作:
 *
 * 1、检查提交的进行认证的令牌信息
 * 2、根据令牌信息从数据源(通常为数据库)中获取用户信息
 * 3、对用户信息进行匹配验证。
 * 4、验证通过将返回一个封装了用户信息的AuthenticationInfo实例。
 * 5、验证失败则抛出AuthenticationException异常信息。
 */

public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private UserInfoService userInfoService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        //进行角色的添加和权限的添加
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo  = (UserInfo)principals.getPrimaryPrincipal();
        for(SysRole role:userInfo.getRoleList()){
            //添加角色
            authorizationInfo.addRole(role.getRole());
            //添加權限
            for(SysPermission p:role.getPermissions()){
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserInfo userInfo = userInfoService.findByUsername(username);
        System.out.println("----->>userInfo="+userInfo);
        if(userInfo == null){
            //返回后會報出對應異常
            return null;
        }
        //这里验证authenticationToken和simpleAuthenticationInfo的信息
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPassword(), //密码
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }

}