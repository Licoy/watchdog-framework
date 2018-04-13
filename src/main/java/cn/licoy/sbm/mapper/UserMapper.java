package cn.licoy.sbm.mapper;

import cn.licoy.sbm.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Licoy
 * @version 2018/4/13/14:03
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
}
