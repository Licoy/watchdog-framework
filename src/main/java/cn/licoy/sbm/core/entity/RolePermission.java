package cn.licoy.sbm.core.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Licoy
 * @version 2018/4/16/8:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolePermission implements Serializable {

    @TableId
    private Integer id;

    private Integer rid;

    private Integer pid;

    private static final long serialVersionUID = 1L;
}
