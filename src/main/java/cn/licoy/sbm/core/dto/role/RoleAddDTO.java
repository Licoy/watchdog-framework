package cn.licoy.sbm.core.dto.role;

import cn.licoy.sbm.core.entity.Permission;
import lombok.Data;

import java.util.List;

/**
 * @author Licoy
 * @version 2018/4/19/14:28
 */
@Data
public class RoleAddDTO {

    private String name;

    private List<Permission> permissions;

}
