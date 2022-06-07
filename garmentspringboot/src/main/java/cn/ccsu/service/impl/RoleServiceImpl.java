package cn.ccsu.service.impl;

import cn.ccsu.entity.Role;
import cn.ccsu.mapper.RoleMapper;
import cn.ccsu.service.RoleService;
import cn.ccsu.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public ResponseResult queryAllRole() {
        List<Role> roles = roleMapper.queryAll();
        return ResponseResult.okResult(roles);
    }
}
