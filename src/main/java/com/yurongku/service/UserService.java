package com.yurongku.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yurongku.dto.CreateUserRequest;
import com.yurongku.entity.User;

import java.util.List;

public interface UserService {

    User createUser(CreateUserRequest request);

    IPage<User> pageUsers(Page<User> page, String keyword, Integer status);

    List<User> listAllUsers(Integer status);

    User getByUsername(String username);
}
