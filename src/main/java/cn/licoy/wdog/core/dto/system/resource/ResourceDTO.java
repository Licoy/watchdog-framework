package cn.licoy.wdog.core.dto.system.resource;

import lombok.Data;

/**
 * @author licoy.cn
 * @version 2018/4/22
 */
@Data
public class ResourceDTO {

    private String name;

    private String parentId;

    private Short type;

    private String url;

    private String permission;

    private String icon;

    private Long sort;

}
