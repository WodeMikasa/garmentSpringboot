package cn.ccsu.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {

    List<String> selectPermsByUserId(@Param("id") Integer id);
}
