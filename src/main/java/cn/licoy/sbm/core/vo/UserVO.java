package cn.licoy.sbm.core.vo;

import cn.licoy.sbm.core.entity.Role;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Licoy
 * @version 2018/4/18/11:34
 */
@Data
public class UserVO {

    private Long id;
    private String username;
    private Integer age;
    private Integer status;
    private List<Role> roles;
    private Date createDate;

}
