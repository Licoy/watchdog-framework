package cn.licoy.wdog.core.service.system.impl;

import cn.licoy.wdog.common.bean.RequestResult;
import cn.licoy.wdog.common.bean.StatusEnum;
import cn.licoy.wdog.common.exception.RequestException;
import cn.licoy.wdog.core.dto.SignInDTO;
import cn.licoy.wdog.core.dto.user.FindUserDTO;
import cn.licoy.wdog.core.entity.system.SysUser;
import cn.licoy.wdog.core.mapper.system.SysUserMapper;
import cn.licoy.wdog.core.service.system.SysRoleService;
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
import java.util.List;

@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements SysUserService {

    @Resource
    private SysRoleService roleService;

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
        SysUser sysUser = (SysUser) subject.getPrincipal();
        if(sysUser==null){
            throw new RequestException(StatusEnum.FAIL.code,"用户信息获取失败");
        }
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
    public RequestResult statusChange(Long userId, Integer status) {
        SysUser user = this.selectById(userId);
        if(user==null){
            throw new RequestException(StatusEnum.FAIL.code,"用户不存在");
        }
        if(user.getUsername().equals(SecurityUtils.getSubject().getPrincipal().toString())){
            throw new RequestException(StatusEnum.FAIL.code,"不能锁定自己的账户");
        }
        user.setStatus(status);
        this.updateById(user);
        return RequestResult.e(StatusEnum.OK);
    }

    @Override
    public RequestResult removeUser(Long userId) {
        SysUser user = this.selectById(userId);
        if(user==null){
            throw new RequestException(StatusEnum.FAIL.code,"用户不存在！");
        }
        if(user.getUsername().equals(SecurityUtils.getSubject().getPrincipal().toString())){
            throw new RequestException(StatusEnum.FAIL.code,"不能删除自己的账户！");
        }
        this.deleteById(userId);
        return RequestResult.e(StatusEnum.OK);
    }
}
