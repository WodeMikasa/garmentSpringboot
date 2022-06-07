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

    @Insert("insert into sys_goods(goodsname,goodscount,size,color,goodsnumber,create_time,isDelete) values (#{goodsName},#{goodsCount},#{size},#{color},#{goodsNumber},#{createTime},#{isDelete})")
    Integer insertGoods(Goods goods);

    @Select("SELECT * FROM sys_goods where goodsname LIKE concat('%', #{name}, '%')  AND color LIKE concat('%', #{number}, '%')")
    List<Goods> selectBy(@Param("name") String name,@Param("number") String number);


    Integer updateGoods(Goods goods);
}
