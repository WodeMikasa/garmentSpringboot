package cn.ccsu.service;

import cn.ccsu.utils.ResponseResult;

public interface UserRoleLinkService {

    ResponseResult roledis(Integer userId, String roleKey);
}
