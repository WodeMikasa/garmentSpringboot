package cn.ccsu.mapper;

import cn.ccsu.entity.OutStore;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface OutStoreMapper {
//    @Insert("insert into sys_outstore(number,storage,agent,towhere,outTime,remark ,create_time,update_time,isDelete) values (#{number},#{storage},#{agent},#{towhere},#{outTime},#{remark},#{createTime},#{updateTime},#{isDelete})")
    Integer insert(OutStore outStore);
    @Select("SELECT * FROM sys_outstore where numbe  LIKE concat('%', #{number}, '%')  AND storage LIKE concat('%', #{storage}, '%') and isDelete='1'")
    List<OutStore> query(@Param("number") String number,@Param("storage") String storage);
    @Update("update sys_outstore set isDelete='0' where id=#{id}")
    Integer delete(@Param("id") Integer id);

    Integer update(OutStore outStore);
}
