package cn.licoy.sbm.core.service.impl;

import cn.licoy.sbm.core.dto.SignInDTO;
import cn.licoy.sbm.core.entity.User;
import cn.licoy.sbm.core.mapper.UserMapper;
import cn.licoy.sbm.core.service.RoleService;
import cn.licoy.sbm.core.service.UserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.annotation.Resource;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Resource
    private RoleService roleService;

    @Override
    public User findUserById(Integer id) {
        return this.selectById(id);
    }

    @Override
    public User findUserByName(String name) {
        User user = this.selectOne(new EntityWrapper<User>().eq("name",name));
        if(user == null){
            return null;
        }
        user.setRole(roleService.findRoleById(user.getRoleId()));
        return user;
    }

    @Override
    public Boolean signIn(SignInDTO signInDTO, Model model) {
        if( "".equals(signInDTO.getUsername()) || "".equals(signInDTO.getPassword()) ){
            model.addAttribute("singInMsg","用户名或密码均不能为空！");
            return false;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(signInDTO.getUsername(),signInDTO.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            subject.getSession().setTimeout(-10001); //session永不超时
            if(subject.isAuthenticated()){
                return true;
            }
            model.addAttribute("singInMsg","登录失败，账号或密码错误！");
            return false;
        }catch (DisabledAccountException e){
            model.addAttribute("singInMsg",e.getMessage());
            return false;
        }catch (Exception e){
            return false;
        }
    }
}
