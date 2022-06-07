package cn.ccsu.service.impl;

import cn.ccsu.entity.LoginUser;
import cn.ccsu.entity.User;
import cn.ccsu.ex.SystemException;
import cn.ccsu.mapper.MenuMapper;
import cn.ccsu.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userMapper.selectByName(username);
        //判断是否查到用户  如果没查到抛出异常

        if(Objects.isNull(user)){
            throw new SystemException("用户不存在");
        }
        //返回用户信息
        // TODO 查询权限信息封装
        List<String> list=menuMapper.selectPermsByUserId(user.getId());
        return new LoginUser(user,list);
    }
}