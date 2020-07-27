package com.zyd.learn.security.dao.entities;

import lombok.Data;

@Data
class Authority {
    private Integer id;
    private String name;
    private String url;
    private Authority parent;
}
