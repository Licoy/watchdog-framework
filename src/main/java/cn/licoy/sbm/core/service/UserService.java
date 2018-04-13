package cn.licoy.sbm.core.service;

import cn.licoy.sbm.core.dto.SignInDTO;
import cn.licoy.sbm.core.entity.User;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.ui.Model;

public interface UserService extends IService<User> {
    User findUserById(Integer id);

    User findUserByName(String name);

    Boolean signIn(SignInDTO signInDTO, Model model);
}
