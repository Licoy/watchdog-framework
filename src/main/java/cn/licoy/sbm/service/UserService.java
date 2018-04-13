package cn.licoy.sbm.service;

import cn.licoy.sbm.entity.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * @author Licoy
 * @version 2018/4/13/14:04
 */
public interface UserService extends IService<User> {

    User findUserById(Integer id);

}
