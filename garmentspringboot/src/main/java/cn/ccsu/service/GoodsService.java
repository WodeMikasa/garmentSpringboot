package cn.ccsu.service;

import cn.ccsu.entity.Goods;
import cn.ccsu.utils.ResponseResult;

public interface GoodsService {
    ResponseResult deleteById(Integer id);

    ResponseResult queryAll(Integer pageNum, Integer pageSize);

    ResponseResult addGoods(Goods goods);

    ResponseResult updateGoods(Goods goods);


    ResponseResult queryBy(Integer pageNum, Integer pageSize, String goodsName, String color);
}
