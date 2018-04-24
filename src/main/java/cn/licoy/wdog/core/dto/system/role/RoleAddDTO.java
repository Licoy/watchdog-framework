package cn.licoy.wdog.core.dto.system.role;

import cn.licoy.wdog.core.entity.system.SysResource;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Licoy
 * @version 2018/4/19/14:28
 */
@Data
public class RoleAddDTO {

    @NotBlank(message = "姓名不能为空")
    private String name;

    @Size(min = 1,message = "请至少选择一个权限资源")
    private List<SysResource> resources;

}
