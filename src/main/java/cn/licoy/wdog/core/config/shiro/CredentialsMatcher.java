package cn.licoy.wdog.core.config.shiro;


import cn.licoy.encryptbody.util.MD5EncryptUtil;
import cn.licoy.wdog.common.util.Encrypt;
import cn.licoy.wdog.common.util.JwtUtil;
import cn.licoy.wdog.core.config.jwt.JwtToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @author licoy.cn
 * @version 2017/9/25
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        JwtToken jwtToken = (JwtToken) token;
        Object accountCredentials = getCredentials(info);
        if(jwtToken.getPassword()!=null){
            Object tokenCredentials = MD5EncryptUtil.encrypt(String.valueOf(
                    jwtToken.getPassword())+jwtToken.getUsername());
            if(!accountCredentials.equals(tokenCredentials)){
                throw new DisabledAccountException("密码不正确！");
            }
        }else{
            boolean verify = JwtUtil.verify(jwtToken.getToken(), jwtToken.getUsername(), accountCredentials.toString());
            if(!verify){
                throw new DisabledAccountException("verifyFail");
            }
        }
        return true;
    }

}
