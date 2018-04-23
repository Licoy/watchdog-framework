package cn.licoy.wdog.core.mapper;

import cn.licoy.wdog.core.entity.UserRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Licoy
 * @version 2018/4/16/11:31
 */
@Mapper
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {
}
