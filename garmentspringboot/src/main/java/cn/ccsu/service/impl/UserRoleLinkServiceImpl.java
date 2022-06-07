package cn.ccsu.service.impl;

import cn.ccsu.entity.UserRoleLink;
import cn.ccsu.mapper.RoleMapper;
import cn.ccsu.mapper.UserRoleLinkMapper;
import cn.ccsu.service.UserRoleLinkService;
import cn.ccsu.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserRoleLinkServiceImpl implements UserRoleLinkService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleLinkMapper userRoleLinkMapper;

    @Override
    public ResponseResult roledis(Integer userId, String roleKey) {
        Integer roleId = null;
        if (StringUtils.hasText(roleKey)) {
            roleId = roleMapper.selectByKey(roleKey);

        }


        UserRoleLink userRoleLink = new UserRoleLink();
        userRoleLink.setUserId(userId);
        userRoleLink.setRoleId(roleId);
        userRoleLinkMapper.addLink(userRoleLink);

        return ResponseResult.okResult();
    }
}
