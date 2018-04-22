package cn.licoy.sbm.core.service.system;

import cn.licoy.sbm.common.bean.RequestResult;
import cn.licoy.sbm.core.dto.role.FindRoleDTO;
import cn.licoy.sbm.core.dto.role.RoleUpdateDTO;
import cn.licoy.sbm.core.entity.Role;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface RoleService extends IService<Role> {
    /**
     * 获取指定ID用户的所有角色（并附带查询所有的角色的权限）
     * @param uid 用户ID
     * @return 角色集合
     */
    List<Role> findAllRoleByUserId(Long uid);

    /**
     * 分页查找所有的角色列表
     * @param findRoleDTO 过滤DTO
     * @return 角色集合
     */
    Page<Role> getList(FindRoleDTO findRoleDTO);

    /**
     * 删除指定ID的角色
     * @param rid 角色ID
     */
    void removeById(Long rid);

    /**
     * 更新指定ID的角色数据
     * @param rid 角色ID
     * @param roleUpdateDTO 更新数据DTO
     */
    void update(Long rid, RoleUpdateDTO roleUpdateDTO);
}
