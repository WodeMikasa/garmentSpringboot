package cn.ccsu.controller;

import cn.ccsu.entity.User;
import cn.ccsu.service.UserService;
import cn.ccsu.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        return userService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult logout(){
        return userService.logout();
    }
    @GetMapping("/info")
    public ResponseResult info(){
        return userService.getInfo();
    }
    @GetMapping("/getAll/{pageNum}/{pageSize}")
    public ResponseResult getAll(@PathVariable Integer pageNum, @PathVariable Integer pageSize){
        //PageInfo<User> pageInfo=userService.getUsersByPage(pageNum,pageSize);
        return ResponseResult.okResult();
    }
    @PostMapping("/add")
    public ResponseResult addUser(@RequestBody User user){
        return userService.addUser(user);
    }
}