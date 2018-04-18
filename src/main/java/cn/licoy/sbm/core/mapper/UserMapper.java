package cn.licoy.sbm.core.mapper;

import cn.licoy.sbm.core.entity.User;
import cn.licoy.sbm.core.vo.UserVO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

}
