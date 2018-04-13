package cn.licoy.sbm.service.impl;

import cn.licoy.sbm.entity.User;
import cn.licoy.sbm.mapper.UserMapper;
import cn.licoy.sbm.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Licoy
 * @version 2018/4/13/14:04
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Override
    public User findUserById(Integer id){
        return this.selectById(id);
    }


}
