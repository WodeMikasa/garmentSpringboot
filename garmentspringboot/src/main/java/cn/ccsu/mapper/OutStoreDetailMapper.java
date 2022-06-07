package cn.ccsu.mapper;

import cn.ccsu.entity.OutStoreDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OutStoreDetailMapper {
//    @Insert("insert into sys_outstoredetail(name,count ,size,color,goodsId,create_time,update_time) values (#{name},#{count},#{size},#{color},#{goodsId},#{createTime},#{updateTime})")
    Integer insert(OutStoreDetail outStoreDetail);
    @Select("select * from sys_outstoredetail where id=#{id}")
    OutStoreDetail selectById(@Param("id") Integer id);
}
