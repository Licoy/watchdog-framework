package cn.licoy.sbm.core.config.shiro;


import cn.licoy.sbm.common.util.Encrypt;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @author licoy.cn
 * @version 2017/9/25
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken authcToken = (UsernamePasswordToken) token;
        Object tokenCredentials = Encrypt.md5(String.valueOf(authcToken.getPassword())+authcToken.getUsername());
        Object accountCredentials = getCredentials(info);
        if(!accountCredentials.equals(tokenCredentials)){
            throw new DisabledAccountException("密码不正确！");
        }
        return true;
    }

}
