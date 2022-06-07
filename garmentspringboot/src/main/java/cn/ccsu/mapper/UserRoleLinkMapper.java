package cn.ccsu.mapper;

import cn.ccsu.entity.UserRoleLink;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserRoleLinkMapper {
    @Insert("insert into sys_user_role(userId,roleId) values (#{userId},#{roleId})")
    Integer addLink(UserRoleLink userRoleLink);
    @Select("select roleId from sys_user_role where userId=#{id}")
    List<Integer> selectByUserId(@Param("id") Integer id);
}
