package cn.licoy.sbm.core.service.impl;

import cn.licoy.sbm.core.entity.UserRole;
import cn.licoy.sbm.core.mapper.UserRoleMapper;
import cn.licoy.sbm.core.service.UserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Licoy
 * @version 2018/4/16/11:32
 */
@Service
@Transactional
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper,UserRole> implements UserRoleService {
}
