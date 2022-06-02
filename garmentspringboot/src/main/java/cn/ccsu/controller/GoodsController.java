package cn.ccsu.controller;

import cn.ccsu.entity.Goods;
import cn.ccsu.mapper.GoodsMapper;
import cn.ccsu.service.GoodsService;
import cn.ccsu.utils.ResponseResult;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsService goodsService;
    @DeleteMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Integer id){
        return goodsService.deleteById(id);
    }

    @GetMapping("/query")
    public ResponseResult pageQuery(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        return goodsService.queryAll(pageNum,pageSize);
    }

    @PostMapping("/add")
    public ResponseResult addGoods(@RequestBody Goods goods){
        return goodsService.addGoods(goods);
    }

    @PostMapping("/update")
    public ResponseResult updateGoods(@RequestBody Goods goods){
        return goodsService.updateGoods(goods);
    }

//    @GetMapping("/queryBy")
//    public ResponseResult queryBy(@RequestParam(defaultValue = "") String goodsName,@RequestParam(defaultValue = "") String color){
//        List<Goods> goods = goodsMapper.selectBy(goodsName, color);
//        return ResponseResult.okResult(goods);
//    }

    @GetMapping("/queryBy")
    public ResponseResult queryBy(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam(defaultValue = "") String goodsName,@RequestParam(defaultValue = "") String color){
        return goodsService.queryBy(pageNum,pageSize,goodsName,color);
    }
}
