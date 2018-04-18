package cn.licoy.sbm.core.service;

import cn.licoy.sbm.common.bean.RequestResult;
import cn.licoy.sbm.core.dto.SignInDTO;
import cn.licoy.sbm.core.dto.user.FindUserDTO;
import cn.licoy.sbm.core.entity.User;
import cn.licoy.sbm.core.vo.UserVO;
import com.baomidou.mybatisplus.service.IService;

public interface UserService extends IService<User> {

    /**
     * 根据用户名查找用户
     * @param name
     * @return
     */
    User findUserByName(String name);

    /**
     * 用户登录操作
     * @param signInDTO
     */
    void signIn(SignInDTO signInDTO);

    /**
     * 获取当前登录用户信息
     * @return
     */
    UserVO getCurrentUser();

    RequestResult getAllUserBySplitPage(FindUserDTO findUserDTO);

    RequestResult statusChange(Integer userId, Integer status);
}
