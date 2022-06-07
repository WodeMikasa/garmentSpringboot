package cn.ccsu.mapper;

import cn.ccsu.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleMapper {
    @Select("select * from sys_role where status='1' and isDelete='1'")
    List<Role> queryAll();
    @Select("select * from sys_role where id=#{id}")
    Role selectById(@Param("id") Integer id);
    @Select("select id from sys_role where rolekey=#{roleKey}")
    Integer selectByKey(@Param("roleKey") String roleKey);
}
