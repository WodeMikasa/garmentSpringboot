package cn.ccsu.service.impl;

import cn.ccsu.entity.LoginUser;
import cn.ccsu.entity.Role;
import cn.ccsu.entity.User;
import cn.ccsu.enums.AppHttpCodeEnum;
import cn.ccsu.ex.SystemException;
import cn.ccsu.mapper.RoleMapper;
import cn.ccsu.mapper.UserMapper;
import cn.ccsu.mapper.UserRoleLinkMapper;
import cn.ccsu.service.UserService;
import cn.ccsu.utils.JwtUtil;
import cn.ccsu.utils.RedisCache;
import cn.ccsu.utils.ResponseResult;
import cn.ccsu.vo.UserInfo;
import cn.ccsu.vo.UserLoginInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRoleLinkMapper userRoleLinkMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;
    @Override
    public ResponseResult delete(Integer id) {
        Integer integer = userMapper.deleteById(id);
        return ResponseResult.okResult(integer);
    }

    @Override
    public ResponseResult query(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟分页设置的后的第一个select查询会被分页查询
        List<User> all = userMapper.findAll();
        //3.PageInfo参数navigatepage（导航页，显示的页码）：默认显示5个连续页,页码导航连续显示的页数5
        PageInfo<User> bookPageInfo = new PageInfo<>(all,5);


        return ResponseResult.okResult(bookPageInfo);
    }

    @Override
    public ResponseResult updateById(User user) {
        return ResponseResult.okResult(userMapper.updateUser(user));
    }

    @Override
    public ResponseResult addUser(User user) {
        Integer integer = userMapper.insertUser(user);
        return ResponseResult.okResult(integer);
    }

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userid 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);

        //把token和userinfo封装 返回
        //把User转换成UserInfoVo
//        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
//         userInfoVo.setRole(userMapper.selectRoleById(Integer.parseInt(userId)));
        UserLoginInfoVo vo = new UserLoginInfoVo(jwt);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult logout() {
        //获取token 解析获取userid
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //获取userid
        Integer userId = loginUser.getUser().getId();
        //删除redis中的用户信息
        redisCache.deleteObject("login:"+userId);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //获取userid
        Integer userId = loginUser.getUser().getId();
        List<Integer> integers = userRoleLinkMapper.selectByUserId(userId);
        List<Role> list=new ArrayList<>();
        for (Integer roleId:integers){
            Role role = roleMapper.selectById(roleId);
            list.add(role);

        }
        User user=userMapper.getById(userId);
        UserInfo userInfo=new UserInfo(user.getUsername());
        userInfo.setRoles(list);
        userInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return ResponseResult.okResult(userInfo);
    }

    @Override
    public ResponseResult updatePassword(Map<String, String> passwordInfo) {
        String oldPassword = passwordInfo.get("oldPassword");
        String newPassword = passwordInfo.get("newPassword");
        if (oldPassword.equals(newPassword)){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_ERROR);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String password = loginUser.getPassword();

        System.out.println(password);
        if(!passwordEncoder.matches(oldPassword,password)){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_ERROR2);
        }
        String encode = passwordEncoder.encode(newPassword);
        Integer id = loginUser.getUser().getId();
        Integer integer = userMapper.updatePassword(encode, id);


        return ResponseResult.okResult(integer);
    }



}
