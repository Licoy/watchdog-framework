package cn.licoy.sbm.core.config.shiro;



import cn.licoy.sbm.common.exception.RequestException;
import cn.licoy.sbm.core.entity.User;
import cn.licoy.sbm.core.service.UserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author licoy.cn
 * @version 2017/9/22
 */
@Component
@Slf4j
public class MyRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("Shiro权限验证执行");
        String username = (String) principalCollection.getPrimaryPrincipal();
        if(username!=null){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            User user = userService.findUserByName(username);
            if(user!=null){
                if(user.getRole()!=null){
                    info.addRole(user.getRole().getName());
                    if(user.getRole().getPermissions()!=null){
                        user.getRole().getPermissions().forEach(v-> info.addStringPermission(v.getPermission()));
                    }
                }
                return info;
            }
        }
        throw new DisabledAccountException("用户信息异常，请重新登录！");
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = null;
        try {
            user = userService.findUserByName(token.getUsername());
        }catch (RequestException e){
            throw new DisabledAccountException(e.getMsg());
        }
        if(user==null){
            throw new DisabledAccountException("用户不存在！");
        }
        return new SimpleAuthenticationInfo(user.getName(),user.getPassword(),getName());
    }

    @PostConstruct
    public void initCredentialsMatcher() {
        //该句作用是重写shiro的密码验证，让shiro用我自己的验证
        setCredentialsMatcher(new CredentialsMatcher());
    }
}
