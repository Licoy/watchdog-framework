package cn.licoy.sbm.core.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable  {

    
    @TableId
    private Integer id;
    private Integer roleId;
    private String name;
    private Integer age;
    private String password;
    @TableField(exist = false)
    private Role role;

    private static final long serialVersionUID = 1L;

}
