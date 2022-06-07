package cn.ccsu.mapper;

import cn.ccsu.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Update("update sys_user set isDelete='0' where id=#{id}")
    Integer deleteById(@Param("id") Integer id);
    @Select("select * from sys_user where isDelete='1'")
    List<User> findAll();
    @Insert("insert into sys_user(username,password,nickname,create_time,phone,address) values (#{username},#{password},#{nickname},#{createTime},#{phone},#{address})")
    Integer insertUser(User user);
    Integer updateUser(User user);
    @Select("select count(*) from sys_user)")
    Integer selectTotal();

    User getById(Integer userId);

    User selectByName(String username);
    @Update("update sys_user set password=#{newPassword} where id=#{id}")
    Integer updatePassword(@Param("newPassword") String password,@Param("id") Integer id);
}
