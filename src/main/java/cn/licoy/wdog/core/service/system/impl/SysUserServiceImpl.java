package cn.licoy.wdog.core.service.system.impl;

import cn.licoy.encryptbody.util.MD5EncryptUtil;
import cn.licoy.wdog.common.bean.ResponseCode;
import cn.licoy.wdog.common.exception.RequestException;
import cn.licoy.wdog.common.util.Encrypt;
import cn.licoy.wdog.common.util.Tools;
import cn.licoy.wdog.core.config.jwt.JwtToken;
import cn.licoy.wdog.core.dto.SignInDTO;
import cn.licoy.wdog.core.dto.system.user.FindUserDTO;
import cn.licoy.wdog.core.dto.system.user.ResetPasswordDTO;
import cn.licoy.wdog.core.dto.system.user.UserAddDTO;
import cn.licoy.wdog.core.dto.system.user.UserUpdateDTO;
import cn.licoy.wdog.core.entity.system.SysResource;
import cn.licoy.wdog.core.entity.system.SysRole;
import cn.licoy.wdog.core.entity.system.SysUser;
import cn.licoy.wdog.core.entity.system.SysUserRole;
import cn.licoy.wdog.core.mapper.system.SysUserMapper;
import cn.licoy.wdog.core.service.global.ShiroService;
import cn.licoy.wdog.core.service.system.SysResourceService;
import cn.licoy.wdog.core.service.system.SysRoleService;
import cn.licoy.wdog.core.service.system.SysUserRoleService;
import cn.licoy.wdog.core.service.system.SysUserService;
import cn.licoy.wdog.core.vo.system.SysUserVO;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements SysUserService {

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysUserRoleService userRoleService;

    @Autowired
    private ShiroService shiroService;

    @Autowired
    private SysResourceService resourceService;

    @Override
    public SysUser findUserByName(String name,boolean hasResource) {
        SysUser user = this.selectOne(new EntityWrapper<SysUser>().eq("username",name));
        if(user == null){
            return null;
        }
        user.setRoles(roleService.findAllRoleByUserId(user.getId(),hasResource));
        return user;
    }

    @Override
    public SysUser findUserById(String id,boolean hasResource) {
        SysUser user = this.selectById(id);
        if(user == null){
            return null;
        }
        user.setRoles(roleService.findAllRoleByUserId(user.getId(),false));
        return user;
    }

    @Override
    public void signIn(SignInDTO signInDTO) {
        if( "".equals(signInDTO.getUsername()) || "".equals(signInDTO.getPassword()) ){
            throw new RequestException(ResponseCode.SING_IN_INPUT_EMPTY);
        }
        JwtToken token = new JwtToken(null,signInDTO.getUsername(),signInDTO.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            if(!subject.isAuthenticated()){
                throw new RequestException(ResponseCode.SIGN_IN_INPUT_FAIL);
            }
        }catch (DisabledAccountException e){
            throw new RequestException(ResponseCode.SIGN_IN_INPUT_FAIL.code,e.getMessage(),e);
        }catch (Exception e){
            throw new RequestException(ResponseCode.SIGN_IN_FAIL,e);
        }
    }


    public SysUserVO getCurrentUser(){
        Tools.executeLogin();
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            throw new RequestException(ResponseCode.NOT_SING_IN);
        }
        JwtToken jwtToken = new JwtToken();
        Object principal = subject.getPrincipal();
        if(principal==null){
            throw RequestException.fail("用户信息获取失败");
        }
        BeanUtils.copyProperties(principal,jwtToken);
        SysUser user = this.findUserByName(jwtToken.getUsername(),false);
        if(user==null){
            throw RequestException.fail("用户不存在");
        }
        //获取菜单/权限信息
        List<SysResource> allPer = userRolesRegexResource(roleService.findAllRoleByUserId(user.getId(),true));
        SysUserVO vo = new SysUserVO();
        BeanUtils.copyProperties(user,vo);
        vo.setResources(allPer);
        return vo;
    }

    @Override
    public List<String> getAllPermissionTag() {
        Tools.executeLogin();
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            throw new RequestException(ResponseCode.NOT_SING_IN);
        }
        JwtToken jwtToken = new JwtToken();
        Object principal = subject.getPrincipal();
        if(principal==null){
            throw RequestException.fail("用户信息获取失败");
        }
        BeanUtils.copyProperties(principal,jwtToken);
        SysUser user = this.selectOne(new EntityWrapper<SysUser>()
                .eq("username",jwtToken.getUsername())
                .setSqlSelect("id"));
        if(user==null){
            throw RequestException.fail("用户不存在");
        }
        List<SysRole> allRoleByUserId = roleService.findAllRoleByUserId(user.getId(), true);
        List<String> permissions = new LinkedList<>();
        for (SysRole sysRole : allRoleByUserId) {
            if(sysRole.getResources()!=null && sysRole.getResources().size()>0){
                sysRole.getResources().forEach(s-> permissions.add(s.getPermission()));
            }
        }
        return permissions;
    }

    public List<SysResource> userRolesRegexResource(List<SysRole> roles){
        if(roles!=null && roles.size()>0){
            Map<String,SysResource> resourceMap = new LinkedHashMap<>();
            roles.forEach(role -> {
                if(role.getResources()!=null && role.getResources().size()>0){
                    role.getResources().forEach(resource -> //含有则不覆盖
                            resourceMap.putIfAbsent(resource.getId(), resource));
                }
            });
            Map<String,SysResource> cacheMap = new ConcurrentHashMap<>();
            List<SysResource> resourceList = new CopyOnWriteArrayList<>();
            resourceMap.forEach((k,v)-> {
                SysResource allParent = resourceService.getResourceAllParent(v, cacheMap,resourceMap);
                //判断是否已经包含此对象
                if(!resourceList.contains(allParent)){
                    resourceList.add(allParent);
                }
            });
            return resourceList;
        }
        return null;
    }

    @Override
    public Page<SysUserVO> getAllUserBySplitPage(FindUserDTO findUserDTO) {
        EntityWrapper<SysUser> wrapper = new EntityWrapper<>();
        wrapper.orderBy("create_date",findUserDTO.getAsc());
        Page<SysUser> userPage = this.selectPage(new Page<>(findUserDTO.getPage(),
                findUserDTO.getPageSize()), wrapper);
        Page<SysUserVO> userVOPage = new Page<>();
        BeanUtils.copyProperties(userPage,userVOPage);
        List<SysUserVO> userVOS = new ArrayList<>();
        userPage.getRecords().forEach(v->{
            SysUserVO vo = new SysUserVO();
            BeanUtils.copyProperties(v,vo);
            //查找匹配所有用户的角色
            vo.setRoles(roleService.findAllRoleByUserId(v.getId(),false));
            userVOS.add(vo);
        });
        userVOPage.setRecords(userVOS);
        return userVOPage;
    }

    @Override
    public void statusChange(String userId, Integer status) {
        SysUser user = this.selectById(userId);
        if(user==null){
            throw RequestException.fail("用户不存在");
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(SecurityUtils.getSubject().getPrincipal(),sysUser);
        if(user.getUsername().equals(sysUser.getUsername())){
            throw RequestException.fail("不能锁定自己的账户");
        }
        user.setStatus(status);
        try {
            this.updateById(user);
            //若为0 需要进行清除登陆授权以及权限信息
            /*if(status==0){

            }*/
            shiroService.clearAuthByUserId(userId,true,true);
        }catch (Exception e){
            throw RequestException.fail("操作失败",e);
        }
    }

    @Override
    public void removeUser(String userId) {
        SysUser user = this.selectById(userId);
        if(user==null){
            throw RequestException.fail("用户不存在！");
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(SecurityUtils.getSubject().getPrincipal(),sysUser);
        if(user.getUsername().equals(sysUser.getUsername())){
            throw RequestException.fail("不能删除自己的账户！");
        }
        try {
            this.deleteById(userId);
            shiroService.clearAuthByUserId(userId,true,true);
        }catch (Exception e){
            throw RequestException.fail("删除失败",e);
        }
    }

    @Override
    public void add(UserAddDTO addDTO) {
        SysUser findUser = this.findUserByName(addDTO.getUsername(),false);
        if(findUser!=null){
            throw RequestException.fail(
                    String.format("已经存在用户名为 %s 的用户",addDTO.getUsername()));
        }
        try {
            findUser = new SysUser();
            BeanUtils.copyProperties(addDTO,findUser);
            findUser.setCreateDate(new Date());
            findUser.setPassword(MD5EncryptUtil.encrypt(String.valueOf(findUser.getPassword())+findUser.getUsername()));
            this.insert(findUser);
            this.updateUserRole(findUser);
        }catch (Exception e){
            throw RequestException.fail("添加用户失败",e);
        }
    }

    @Override
    public void update(String id, UserUpdateDTO updateDTO) {
        SysUser user = this.selectById(id);
        if(user==null){
            throw RequestException.fail(
                    String.format("更新失败，不存在ID为 %s 的用户",id));
        }
        SysUser findUser = this.selectOne(new EntityWrapper<SysUser>()
                    .eq("username",updateDTO.getUsername()).ne("id",id));
        if(findUser!=null){
            throw RequestException.fail(
                    String.format("更新失败，已经存在用户名为 %s 的用户",updateDTO.getUsername()));
        }
        BeanUtils.copyProperties(updateDTO,user);
        try {
            this.updateById(user);
            this.updateUserRole(user);
            shiroService.clearAuthByUserId(user.getId(),true,false);
        }catch (RequestException e){
            throw RequestException.fail(e.getMsg(),e);
        }catch (Exception e){
            throw RequestException.fail("用户信息更新失败",e);
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
            throw RequestException.fail("用户权限关联失败",e);
        }
    }

    @Override
    public void resetPassword(ResetPasswordDTO resetPasswordDTO){
        SysUser user = this.selectById(resetPasswordDTO.getUid().trim());
        if(user==null){
            throw RequestException.fail(String.format("不存在ID为 %s 的用户",resetPasswordDTO.getUid()));
        }
        String password = MD5EncryptUtil.encrypt(String.valueOf(resetPasswordDTO.getPassword())+user.getUsername());
        user.setPassword(password);
        try {
            this.updateById(user);
            shiroService.clearAuthByUserId(user.getId(),true,true);
        }catch (Exception e){
            throw RequestException.fail(String.format("ID为 %s 的用户密码重置失败",resetPasswordDTO.getUid()),e);
        }
    }
}
