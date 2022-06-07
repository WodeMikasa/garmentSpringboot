package cn.ccsu.mapper;

import cn.ccsu.entity.Goods;
import cn.ccsu.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GoodsMapper {
    @Update("update sys_goods set isDelete='0' where id=#{id}")
    void delete(@Param("id") Integer id);

    @Select("select * from sys_goods where isDelete='1'")
    List<Goods> findAll();

    @Insert("insert into sys_goods(name,count,size,color,number,create_time,isDelete) values (#{goodsName},#{goodsCount},#{size},#{color},#{goodsNumber},#{createTime},#{isDelete})")
    Integer insertGoods(Goods goods);

    @Select("SELECT * FROM sys_goods where name LIKE concat('%', #{goodsName}, '%')  AND number LIKE concat('%', #{number}, '%')")
    List<Goods> selectBy(@Param("goodsName") String name,@Param("number") String number);

    @Select("select * from sys_goods where id=#{id}")
    Goods selectById(@Param("id") Integer id);
    @Update("update sys_goods set count=#{count} where id=#{id}")
    Integer updateCount(@Param("id") Integer id,@Param("count") Integer count);


    Integer updateGoods(Goods goods);
}
