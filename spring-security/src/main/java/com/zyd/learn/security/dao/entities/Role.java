package com.zyd.learn.security.dao.entities;

import lombok.Data;

import java.util.List;

/**
 * 一个role对应多个privilege
 */
@Data
public class Role {
    private Integer id;
    private String name;
    List<Authority> authorities;
}
