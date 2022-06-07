package cn.ccsu.mapper;

import cn.ccsu.entity.OutStoreLink;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OutStoreLinkMapper {
    @Insert("insert into sys_outstorelink(outstoreId,outstoredetailId) values (#{outStoreId},#{outStoreDetailId})")
    Integer insert(OutStoreLink outStoreLink);
    @Select("select outstoredetailId from sys_outstorelink where outstoreId=#{id}")
    List<Integer> selectById(@Param("id") Integer id);
}
