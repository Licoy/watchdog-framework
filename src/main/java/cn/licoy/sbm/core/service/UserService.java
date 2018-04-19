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
     * @param name 用户名
     * @return User
     */
    User findUserByName(String name);

    /**
     * 用户登录操作
     * @param signInDTO 登录信息
     */
    void signIn(SignInDTO signInDTO);

    /**
     * 获取当前登录用户信息
     * @return UserVO
     */
    UserVO getCurrentUser();

    /**
     * 获取所有用户（分页）
     * @param findUserDTO 过滤条件
     * @return RequestResult
     */
    RequestResult getAllUserBySplitPage(FindUserDTO findUserDTO);

    /**
     * 用户状态改变
     * @param userId 用户ID
     * @param status 状态码
     * @return RequestResult
     */
    RequestResult statusChange(Long userId, Integer status);

    /**
     * 删除用户
     * @param userId 用户ID
     * @return RequestResult
     */
    RequestResult removeUser(Long userId);
}
