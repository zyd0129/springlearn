package com.zyd.learn.security.service.impl;

import com.zyd.learn.security.dao.entities.UserDO;
import com.zyd.learn.security.dao.mapper.UserMapper;
import com.zyd.learn.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDO getByName(String name) {
        return userMapper.getByName(name);
    }
}
