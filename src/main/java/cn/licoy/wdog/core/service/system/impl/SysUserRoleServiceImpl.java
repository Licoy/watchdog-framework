package cn.licoy.wdog.core.service.system.impl;

import cn.licoy.wdog.core.entity.system.SysUserRole;
import cn.licoy.wdog.core.mapper.system.SysUserRoleMapper;
import cn.licoy.wdog.core.service.system.SysUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Licoy
 * @version 2018/4/16/11:32
 */
@Service
@Transactional
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper,SysUserRole> implements SysUserRoleService {
}
