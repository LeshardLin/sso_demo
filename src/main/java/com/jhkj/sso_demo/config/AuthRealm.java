package com.jhkj.sso_demo.config;

import com.jhkj.face2019_demo.entity.SysUser;
import com.jhkj.face2019_demo.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: LinYan
 * @Date: 2018/9/29 08:38
 * @Description:自定义的realm
 */
public class AuthRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Autowired
    SysUserService sysUserService;
//    @Autowired
//
//    @Autowired
//    PermissionService permissionService;
//
//    //用于授权
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        //TODO 查询当前用户所在的组，从组中获取权限进行授权
//        //从凭证集合中获取当前用户
//        User user = (User) principals.getPrimaryPrincipal();
//
//        //创建角色和权限集合
//        Set<String> roles;
//        Set<String> permissions;
//
//        //获取用户具有的角色id的String类型数组,将数组转为stream流
//        //1.将流中的id转为Role对象
//        //2.将Role对象转为roleName
//        roles = Arrays.stream(user.getRids().split(","))
//                .map(id -> roleService.findById(Long.valueOf(id)))
//                .map(Role::getRoleName)
//                .collect(Collectors.toSet());
//
//        //从角色名称中获取角色对应的权限
//        permissions = roles.stream()
//                .map(roleName -> roleService.findByRoleName(roleName))
//                .map(role -> role.getPids().split(","))
//                .flatMap(Arrays::stream)
//                .map(id -> permissionService.findById(Long.valueOf(id)))
//                .map(Permission::getPermission)
//                .collect(Collectors.toSet());
//
//        //设置权限信息
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.setRoles(roles);
//        authorizationInfo.setStringPermissions(permissions);
//
//        return authorizationInfo;
//    }
//
    //用于认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //将token强转为UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String account = usernamePasswordToken.getUsername();

        //TODO 数据库中可能查询为null，需要进行判断：待验证
        SysUser user = sysUserService.findUser(account);

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        //设置盐值
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));

        return authenticationInfo;
    }

}
