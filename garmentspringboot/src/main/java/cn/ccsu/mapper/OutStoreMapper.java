package cn.ccsu.mapper;

import cn.ccsu.entity.OutStore;
import org.apache.ibatis.annotations.Insert;

public interface OutStoreMapper {
//    @Insert("insert into sys_outstore(number,storage,agent,towhere,outTime,remark ,create_time,update_time,isDelete) values (#{number},#{storage},#{agent},#{towhere},#{outTime},#{remark},#{createTime},#{updateTime},#{isDelete})")
    Integer insert(OutStore outStore);
}
