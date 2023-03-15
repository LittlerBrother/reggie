package com.heima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.reggie.domain.User;
import com.heima.reggie.mapper.UserMapper;
import com.heima.reggie.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: reggie
 * @BelongsPackage: com.heima.reggie.service.impl
 * @Author: Little Brother
 * @CreateTime: 2023-03-14  10:17
 * @Version: 1.0
 * @Description: TODO
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}

