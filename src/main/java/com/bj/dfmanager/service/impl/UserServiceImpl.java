package com.bj.dfmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bj.dfmanager.entity.Role;
import com.bj.dfmanager.entity.User;
import com.bj.dfmanager.mapper.RoleMapper;
import com.bj.dfmanager.mapper.UserMapper;
import com.bj.dfmanager.mapper.UserRoleMapper;
import com.bj.dfmanager.service.UserService;
import com.bj.dfmanager.util.AESUtils;
import com.bj.dfmanager.util.JwtTokenUtils;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.user.UserSearchVO;
import com.bj.dfmanager.vo.user.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    /**
     * 用户登录
     */
    @Override
    public Result login(UserVO userVO) {
        // 用户名、密码非空判断
        if (StringUtils.isEmpty(userVO.getUserName())) {
            return Result.fail(null, "用户名不能为空");
        }
        if (StringUtils.isEmpty(userVO.getPassword())) {
            return Result.fail(null, "密码不能为空");
        }

        // 根据用户名和密码查询用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userVO.getUserName());
        queryWrapper.eq(User::getPassword, AESUtils.encryptN(userVO.getPassword()));
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            // 用户名或密码有误
            return Result.fail(null, "用户名或密码输入错误");
        }
        if ("N".equals(user.getUserStatus())) {
            // 用户不可用
            return Result.fail(null, "用户不可用");
        }
        // 生成jwt token给前端
        String token = JwtTokenUtils.getToken(user.getUserId().toString(),
                AESUtils.encryptN(userVO.getPassword()));
        user.setToken(token);
        user.setUpdateTime(new Date());
        userMapper.updateById(user);

        List<Role> roleList = roleMapper.userHaveRole(user.getUserId());
        user.setRoleList(roleList.stream().map(Role::getRoleId).distinct().collect(Collectors.toList()));

        return Result.success(user, "登录成功");
    }

    /**
     * 根据用户ID查询用户
     */
    @Override
    public User findById(Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * 判断用户名是否存在
     */
    @Override
    public Result userNameIsExist(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userName);
        int count = userMapper.selectCount(queryWrapper).intValue();
        if (count == 0) {
            return Result.success(null, "用户名不存在，可以注册");
        } else {
            return Result.fail(null, "用户名存在，不可以注册");
        }
    }

    /**
     * 添加/修改用户
     */
    @Override
    public Result addOrUpdate(UserVO userVO) {
        int num;
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        user.setPassword(AESUtils.encryptN(user.getPassword()));
        if (null == user.getUserId()) {
            user.setUserStatus("Y");
            user.setCreateTime(new Date());
            num = userMapper.insert(user);
        } else {
            user.setUpdateTime(new Date());
            num = userMapper.updateById(user);
        }
        if (num > 0) {
            return Result.success(null, "操作成功");
        } else {
            return Result.fail(null, "操作失败");
        }
    }

    /**
     * 查询用户列表
     */
    @Override
    public Result queryList(UserSearchVO userSearchVO) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(userSearchVO.getUserName()),
                User::getUserName, userSearchVO.getUserName());
        queryWrapper.like(StringUtils.isNotEmpty(userSearchVO.getRealName()),
                User::getRealName, userSearchVO.getRealName());
        queryWrapper.like(StringUtils.isNotEmpty(userSearchVO.getPoliceNo()),
                User::getPoliceNo, userSearchVO.getPoliceNo());
        queryWrapper.like(StringUtils.isNotEmpty(userSearchVO.getIdCard()),
                User::getIdCard, userSearchVO.getIdCard());
        queryWrapper.eq(StringUtils.isNotEmpty(userSearchVO.getGender()),
                User::getGender, userSearchVO.getGender());
        queryWrapper.eq(StringUtils.isNotEmpty(userSearchVO.getUserStatus()),
                User::getUserStatus, userSearchVO.getUserStatus());
        queryWrapper.eq(StringUtils.isNotEmpty(userSearchVO.getDepartment()),
                User::getDepartment, userSearchVO.getDepartment());
        queryWrapper.orderByDesc(User::getCreateTime);
        IPage<User> page = userMapper.selectPage(new Page<>(userSearchVO.getCurrent(),
                userSearchVO.getSize()), queryWrapper);
        return Result.success(page, "查询用户列表成功");
    }

    /**
     * 查询用户信息
     */
    @Override
    public Result queryById(Integer userId) {
        User user = userMapper.selectById(userId);
        user.setPassword(AESUtils.decryptN(user.getPassword()));
        return Result.success(user, "查询用户信息成功");
    }

    /**
     * 删除用户信息
     */
    @Override
    public Result delete(Integer userId) {
        int num = userMapper.deleteById(userId);
        if (num > 0) {
            // 删除用户绑定的角色
            Map map = new HashMap();
            map.put("USER_ID", userId);
            userRoleMapper.deleteByMap(map);
            return Result.success(null, "删除用户成功");
        } else {
            return Result.fail(null, "删除用户失败");
        }
    }

    /**
     * 启用/停用用户
     */
    @Override
    public Result startOrStop(UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        user.setUpdateTime(new Date());
        int num = userMapper.updateById(user);
        if (num > 0) {
            return Result.success(null, "操作成功");
        } else {
            return Result.fail(null, "操作成功");
        }
    }

    /**
     * 查询用户已拥有的角色
     */
    @Override
    public Result userHaveRole(Integer userId) {
        List<Role> roleList = roleMapper.userHaveRole(userId);
        return Result.success(roleList, "查询用户已拥有的角色成功");
    }

    /**
     * 查询用户未拥有的角色
     */
    @Override
    public Result userNotHaveRole(Integer userId) {
        List<Role> roleList = roleMapper.userNotHaveRole(userId);
        return Result.success(roleList, "查询用户未拥有的角色成功");
    }

    /**
     * 用户绑定角色
     */
    @Override
    public Result userBindRole(UserVO userVO) {
        // 先删除用户已绑定的角色
        Map map = new HashMap();
        map.put("USER_ID", userVO.getUserId());
        userRoleMapper.deleteByMap(map);

        // 绑定新角色
        if (CollectionUtils.isNotEmpty(userVO.getRoleList())) {
            userRoleMapper.userBindRole(userVO.getUserId(), userVO.getRoleList());
        }
        return Result.success(null, "用户绑定角色成功");
    }

    /**
     * 查询用户列表
     */
    @Override
    public Result userList() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserStatus, "Y");
        queryWrapper.orderByDesc(User::getCreateTime);
        List<User> userList = userMapper.selectList(queryWrapper);
        return Result.success(userList, "查询用户列表成功");
    }

}
