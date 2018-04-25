package cn.licoy.wdog.core.service.system.impl;

import cn.licoy.wdog.common.bean.RequestResult;
import cn.licoy.wdog.common.bean.StatusEnum;
import cn.licoy.wdog.common.exception.RequestException;
import cn.licoy.wdog.common.util.Encrypt;
import cn.licoy.wdog.core.dto.SignInDTO;
import cn.licoy.wdog.core.dto.system.user.FindUserDTO;
import cn.licoy.wdog.core.dto.system.user.ResetPasswordDTO;
import cn.licoy.wdog.core.dto.system.user.UserAddDTO;
import cn.licoy.wdog.core.dto.system.user.UserUpdateDTO;
import cn.licoy.wdog.core.entity.system.SysUser;
import cn.licoy.wdog.core.entity.system.SysUserRole;
import cn.licoy.wdog.core.mapper.system.SysUserMapper;
import cn.licoy.wdog.core.service.system.SysRoleService;
import cn.licoy.wdog.core.service.system.SysUserRoleService;
import cn.licoy.wdog.core.service.system.SysUserService;
import cn.licoy.wdog.core.vo.system.SysUserVO;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements SysUserService {

    @Resource
    private SysRoleService roleService;

    @Resource
    private SysUserRoleService userRoleService;

    @Override
    public SysUser findUserByName(String name) {
        SysUser user = this.selectOne(new EntityWrapper<SysUser>().eq("username",name));
        if(user == null){
            return null;
        }
        user.setRoles(roleService.findAllRoleByUserId(user.getId()));
        return user;
    }

    @Override
    public SysUser findUserById(String id) {
        SysUser user = this.selectById(id);
        if(user == null){
            return null;
        }
        user.setRoles(roleService.findAllRoleByUserId(user.getId()));
        return user;
    }

    @Override
    public void signIn(SignInDTO signInDTO) {
        if( "".equals(signInDTO.getUsername()) || "".equals(signInDTO.getPassword()) ){
            throw new RequestException(StatusEnum.SING_IN_INPUT_EMPTY);
        }
        UsernamePasswordToken token = new UsernamePasswordToken(signInDTO.getUsername(),signInDTO.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            subject.getSession().setTimeout(-10001); //session永不超时
            if(!subject.isAuthenticated()){
                throw new RequestException(StatusEnum.SIGN_IN_INPUT_FAIL);
            }
        }catch (DisabledAccountException e){
            throw new RequestException(StatusEnum.SIGN_IN_INPUT_FAIL.code,e.getMessage(),e);
        }catch (Exception e){
            throw new RequestException(StatusEnum.SIGN_IN_FAIL);
        }
    }


    public SysUserVO getCurrentUser(){
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            throw new RequestException(StatusEnum.NOT_SING_IN);
        }
        SysUser sysUser = new SysUser();
        Object principal = subject.getPrincipal();
        if(principal==null){
            throw new RequestException(StatusEnum.FAIL.code,"用户信息获取失败");
        }
        BeanUtils.copyProperties(principal,sysUser);
        SysUser user = this.findUserByName(sysUser.getUsername());
        if(user==null){
            throw new RequestException(StatusEnum.FAIL.code,"用户不存在");
        }
        SysUserVO vo = new SysUserVO();
        BeanUtils.copyProperties(user,vo);
        return vo;
    }

    @Override
    public RequestResult getAllUserBySplitPage(FindUserDTO findUserDTO) {
        EntityWrapper<SysUser> wrapper = new EntityWrapper<>();
        wrapper.orderBy("id",findUserDTO.getAsc());
        Page<SysUser> userPage = this.selectPage(new Page<>(findUserDTO.getPage(),
                findUserDTO.getPageSize()), wrapper);
        Page<SysUserVO> userVOPage = new Page<>();
        BeanUtils.copyProperties(userPage,userVOPage);
        List<SysUserVO> userVOS = new ArrayList<>();
        userPage.getRecords().forEach(v->{
            SysUserVO vo = new SysUserVO();
            BeanUtils.copyProperties(v,vo);
            userVOS.add(vo);
        });
        userVOPage.setRecords(userVOS);
        return RequestResult.e(StatusEnum.OK,userVOPage);
    }

    @Override
    public RequestResult statusChange(String userId, Integer status) {
        SysUser user = this.selectById(userId);
        if(user==null){
            throw new RequestException(StatusEnum.FAIL.code,"用户不存在");
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(SecurityUtils.getSubject().getPrincipal(),sysUser);
        if(user.getUsername().equals(sysUser.getUsername())){
            throw new RequestException(StatusEnum.FAIL.code,"不能锁定自己的账户");
        }
        user.setStatus(status);
        this.updateById(user);
        return RequestResult.e(StatusEnum.OK);
    }

    @Override
    public RequestResult removeUser(String userId) {
        SysUser user = this.selectById(userId);
        if(user==null){
            throw new RequestException(StatusEnum.FAIL.code,"用户不存在！");
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(SecurityUtils.getSubject().getPrincipal(),sysUser);
        if(user.getUsername().equals(sysUser.getUsername())){
            throw new RequestException(StatusEnum.FAIL.code,"不能删除自己的账户！");
        }
        this.deleteById(userId);
        return RequestResult.e(StatusEnum.OK);
    }

    @Override
    public void add(UserAddDTO addDTO) {
        SysUser findUser = this.findUserByName(addDTO.getUsername());
        if(findUser!=null){
            throw new RequestException(StatusEnum.FAIL.code,
                    String.format("已经存在用户名为 %s 的用户",addDTO.getUsername()));
        }
        try {
            findUser = new SysUser();
            BeanUtils.copyProperties(addDTO,findUser);
            findUser.setCreateDate(new Date());
            findUser.setPassword(Encrypt.md5(String.valueOf(findUser.getPassword())+findUser.getUsername()));
            this.insert(findUser);
            this.updateUserRole(findUser);
        }catch (Exception e){
            throw new RequestException(StatusEnum.FAIL.code,"添加用户失败",e);
        }
    }

    @Override
    public void update(String id, UserUpdateDTO updateDTO) {
        SysUser user = this.selectById(id);
        if(user==null){
            throw new RequestException(StatusEnum.FAIL.code,
                    String.format("更新失败，不存在ID为 %s 的用户",id));
        }
        SysUser findUser = this.selectOne(new EntityWrapper<SysUser>()
                    .eq("username",updateDTO.getUsername()).ne("id",id));
        if(findUser!=null){
            throw new RequestException(StatusEnum.FAIL.code,
                    String.format("更新失败，已经存在用户名为 %s 的用户",updateDTO.getUsername()));
        }
        BeanUtils.copyProperties(updateDTO,user);
        try {
            this.updateById(user);
            this.updateUserRole(user);
        }catch (RequestException e){
            throw new RequestException(StatusEnum.FAIL.code,e.getMsg(),e);
        }catch (Exception e){
            throw new RequestException(StatusEnum.FAIL.code,"用户信息更新失败",e);
        }
    }

    @Override
    public void updateUserRole(SysUser user) {
        try {
            userRoleService.delete(new EntityWrapper<SysUserRole>().eq("uid",user.getId()));
            if(user.getRoles()!=null && user.getRoles().size()>0){
                user.getRoles().forEach(v-> userRoleService.insert(SysUserRole.builder()
                        .uid(user.getId())
                        .rid(v.getId()).build()));
            }
        }catch (Exception e){
            throw new RequestException(StatusEnum.FAIL.code,"用户权限关联失败",e);
        }
    }

    public void resetPassword(ResetPasswordDTO resetPasswordDTO){
        SysUser user = this.selectById(resetPasswordDTO.getUid().trim());
        if(user==null){
            throw new RequestException(StatusEnum.FAIL.code,
                    String.format("不存在ID为 %s 的用户",resetPasswordDTO.getUid()));
        }
        String password = Encrypt.md5(String.valueOf(resetPasswordDTO.getPassword())+user.getUsername());
        user.setPassword(password);
        try {
            this.updateById(user);
        }catch (Exception e){
            throw new RequestException(StatusEnum.FAIL.code,
                    String.format("ID为 %s 的用户密码重置失败",resetPasswordDTO.getUid()),e);
        }
    }
}
