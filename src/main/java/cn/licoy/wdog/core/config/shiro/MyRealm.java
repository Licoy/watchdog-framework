package cn.licoy.wdog.core.config.shiro;



import cn.licoy.wdog.common.exception.RequestException;
import cn.licoy.wdog.core.entity.system.SysUser;
import cn.licoy.wdog.core.service.system.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author licoy.cn
 * @version 2017/9/22
 */
@Component
@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Resource
    private SysUserService userService;

    @Resource
    private RedisSessionDAO redisSessionDAO;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("Shiro权限验证执行");
        SysUser user = new SysUser();
        BeanUtils.copyProperties(principalCollection.getPrimaryPrincipal(),user);
        if(user.getId()!=null){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            SysUser findUser = userService.findUserByName(user.getUsername());
            if(findUser!=null){
                if(findUser.getRoles()!=null){
                    findUser.getRoles().forEach(role->{
                        info.addRole(role.getName());
                        if(role.getResources()!=null){
                            role.getResources().forEach(v->{
                                if(!"".equals(v.getPermission().trim())){
                                    info.addStringPermission(v.getPermission());
                                }
                            });
                        }
                    });
                }
                return info;
            }
        }
        throw new DisabledAccountException("用户信息异常，请重新登录！");
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        SysUser user = null;
        try {
            user = userService.findUserByName(token.getUsername());
        }catch (RequestException e){
            throw new DisabledAccountException(e.getMsg());
        }
        if(user==null){
            throw new DisabledAccountException("用户不存在！");
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
    }

    @PostConstruct
    public void initCredentialsMatcher() {
        //该句作用是重写shiro的密码验证，让shiro用我自己的验证
        setCredentialsMatcher(new CredentialsMatcher());
    }

    public void clearAuthByUserId(String uid){
        //获取所有session
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        //定义返回
        List<SimplePrincipalCollection> spcList = new ArrayList<>();
        for (Session session:sessions){
            //获取session登录信息。
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if(obj instanceof SimplePrincipalCollection){
                //强转
                SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
                SysUser user = new SysUser();
                BeanUtils.copyProperties(spc.getPrimaryPrincipal(),user);
                //判断用户，匹配用户ID。
                if(uid.equals(user.getId())){
                    spcList.add(spc);
                }
            }
        }
        spcList.forEach(this::clearCachedAuthenticationInfo);
    }
}
