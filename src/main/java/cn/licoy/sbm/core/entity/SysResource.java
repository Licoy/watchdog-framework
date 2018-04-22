package cn.licoy.sbm.core.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Licoy
 * @version 2018/4/20/16:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysResource {

    @TableId
    private String id;

    private String name;

    private String parentId;

    private Short type;

    private String url;

    private String permission;

    private String icon;

    private Long sort;

    private Date createDate;

    @TableField(exist = false)
    private List<SysResource> children;

}
