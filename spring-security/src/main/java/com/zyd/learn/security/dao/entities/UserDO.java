package com.zyd.learn.security.dao.entities;

import lombok.Data;

@Data
public class UserDO {
    private Integer id;
    private String name;
    private String password;
    /**
     * 需要合并role和authorities,形成最终的authorities
     */
    private String role;
    private String authorities;

}