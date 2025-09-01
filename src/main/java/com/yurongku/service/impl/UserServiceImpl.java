package com.yurongku.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yurongku.dto.CreateUserRequest;
import com.yurongku.entity.User;
import com.yurongku.entity.UserDept;
import com.yurongku.mapper.UserDeptMapper;
import com.yurongku.mapper.UserMapper;
import com.yurongku.service.UserService;
import com.yurongku.utils.PasswordUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserDeptMapper userDeptMapper;

    public UserServiceImpl(UserMapper userMapper, UserDeptMapper userDeptMapper) {
        this.userMapper = userMapper;
        this.userDeptMapper = userDeptMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User createUser(CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(PasswordUtil.encodePassword(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setRealName(request.getRealName());
        user.setAvatar(request.getAvatar());
        user.setStatus(request.getStatus());
        user.setDeptId(request.getDeptId());
        userMapper.insert(user);
        if (request.getDeptId() != null) {  // deptId 不为空才插入
            UserDept userDept = new UserDept();
            userDept.setUserId(user.getId());
            userDept.setDeptId(request.getDeptId());
            userDept.setIsPrimary(1);
            userDept.setJoinDate(LocalDateTime.now());
            userDeptMapper.insert(userDept);
        }
        return user;
    }

    @Override
    public IPage<User> pageUsers(Page<User> page, String keyword, Integer status) {
        return userMapper.selectPageUsers(page, keyword, status);
    }

    @Override
    public List<User> listAllUsers(Integer status) {
        return userMapper.selectAllUsers(status);
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.selectOne(new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<User>()
                .eq("username", username)
                .eq("is_deleted", 0));
    }
}
