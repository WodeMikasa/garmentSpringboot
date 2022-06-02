package cn.ccsu.controller;

import cn.ccsu.entity.LoginUser;
import cn.ccsu.enums.AppHttpCodeEnum;
import cn.ccsu.ex.SystemException;
import cn.ccsu.mapper.UserMapper;
import cn.ccsu.service.UserService;
import cn.ccsu.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/updatePassword")
public class PasswordChangeController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/change")
    public ResponseResult change(@RequestBody Map<String,String> passwordInfo) {
        return userService.updatePassword(passwordInfo);
    }

}
