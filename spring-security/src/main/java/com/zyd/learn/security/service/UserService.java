package com.zyd.learn.security.service;

import com.zyd.learn.security.dao.entities.UserDO;

public interface UserService {
    UserDO getByName(String name);
}
