package com.zyd.learn.security.dao.mapper;

import com.zyd.learn.security.dao.entities.UserDO;
import org.apache.ibatis.annotations.Select;

public interface UserMapper{
    @Select("select * from user where name=#{name}")
    UserDO getByName(String name);
}
