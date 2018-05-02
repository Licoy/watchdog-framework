package cn.licoy.wdog.core.config.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;

@NoArgsConstructor
@Data
public class JwtToken implements AuthenticationToken {

    private String token;

    private String username;

    private String password;

    private String uid;

    public JwtToken(String token, String username, String password) {
        this.token = token;
        this.username = username;
        this.password = password;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
