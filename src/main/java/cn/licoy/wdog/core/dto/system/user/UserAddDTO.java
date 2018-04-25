package cn.licoy.wdog.core.dto.system.user;

import cn.licoy.wdog.core.entity.system.SysRole;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @author Licoy
 * @version 2018/4/24/16:40
 */
@Data
public class UserAddDTO {

    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^(\\w){4,16}$",message = "用户名应为[A-Za-z0-9_]组成的4-16位字符！")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(\\w){6,18}$",message = "密码应为[A-Za-z0-9_]组成的6-18位字符！")
    private String password;

    @NotNull(message = "年龄不能为空")
    private Integer age;

    @NotNull(message = "状态标识不能为空")
    private Integer status;

    @Size(min = 1, message = "请至少选择一个角色")
    private List<SysRole> roles;

}
