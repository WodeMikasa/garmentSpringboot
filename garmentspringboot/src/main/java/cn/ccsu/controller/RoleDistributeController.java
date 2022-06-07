package cn.ccsu.controller;

import cn.ccsu.mapper.RoleMapper;
import cn.ccsu.service.RoleService;
import cn.ccsu.service.UserRoleLinkService;
import cn.ccsu.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleDistributeController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleLinkService userRoleLinkService;

    @GetMapping ("/distribute")
    public ResponseResult roleDis(Integer userId,String roleKey){
        return userRoleLinkService.roledis(userId,roleKey);

    }
    @GetMapping("/getRole")
    public ResponseResult getRole(){
        return roleService.queryAllRole();
    }




}
