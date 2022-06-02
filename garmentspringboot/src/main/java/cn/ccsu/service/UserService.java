package cn.ccsu.service;

import cn.ccsu.entity.User;
import cn.ccsu.utils.ResponseResult;

import java.util.Map;

public interface UserService {
    ResponseResult delete(Integer id);
    ResponseResult query(Integer pageNum,Integer pageSize);

    ResponseResult updateById(User user);

    ResponseResult addUser(User user);

    ResponseResult login(User user);
    ResponseResult logout();
    ResponseResult getInfo();

    ResponseResult updatePassword(Map<String, String> passwordInfo);
}
