package cn.licoy.sbm.core.service.system.impl;

import cn.licoy.sbm.core.entity.Permission;
import cn.licoy.sbm.core.mapper.PermissionMapper;
import cn.licoy.sbm.core.service.system.PermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper,Permission> implements PermissionService {

}
