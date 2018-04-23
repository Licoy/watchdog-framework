package cn.licoy.wdog.core.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * @author licoy.cn
 * @version 2018/1/24
 */
@Data
public class SignInDTO {

    @NotBlank(message = "用户名不可以为空！")
    private String username;

    @NotBlank(message = "密码不可以为空！")
    @Pattern(regexp = "^(\\w){6,18}$",message = "密码应为[A-Za-z0-9_]组成的6-18位字符！")
    private String password;

}
