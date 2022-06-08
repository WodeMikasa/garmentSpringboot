package cn.ccsu.service;

import cn.ccsu.entity.OutStore;
import cn.ccsu.utils.ResponseResult;

public interface OutStoreService {
    ResponseResult outStore(OutStore outStore);

    ResponseResult queryAll(Integer pageNum, Integer pageSize, String number, String storage);

    ResponseResult delete(Integer id);

    ResponseResult update(OutStore outStore);
}
