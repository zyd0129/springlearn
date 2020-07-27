package com.zyd.learn.security.auth;

import com.zyd.learn.security.dao.entities.UserDO;
import com.zyd.learn.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO userDO = userService.getByName(username);
        if (userDO == null) {
            throw new UsernameNotFoundException("not found");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        String authStr = userDO.getAuthorities();
        if(!StringUtils.isEmpty(authStr)){
            String[] authoritiesStr = authStr.split(";");
            for (String aut : authoritiesStr) {
                authorities.add(new SimpleGrantedAuthority(aut));
            }
        }

        return new User(userDO.getName(), userDO.getPassword(), authorities);
    }
}
