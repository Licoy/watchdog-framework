package cn.licoy.wdog.core.entity.system;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Licoy
 * @version 2018/4/16/11:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysUserRole implements Serializable {

    @TableId
    private String id;

    private String uid;

    private String rid;

}
