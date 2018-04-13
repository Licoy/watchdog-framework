package cn.licoy.sbm.core.entity;

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
public class Permission implements Serializable  {

    @TableId
    private Integer id;
    private String name;
    private String permission;
    private Integer roleId;

    private static final long serialVersionUID = 1L;

}
