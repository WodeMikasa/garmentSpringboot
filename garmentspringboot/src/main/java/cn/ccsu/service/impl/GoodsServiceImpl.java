package cn.ccsu.service.impl;

import cn.ccsu.entity.Goods;
import cn.ccsu.entity.User;
import cn.ccsu.mapper.GoodsMapper;
import cn.ccsu.service.GoodsService;
import cn.ccsu.utils.BeanCopyUtils;
import cn.ccsu.utils.ResponseResult;
import cn.ccsu.vo.GoodsVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public ResponseResult deleteById(Integer id) {
         goodsMapper.delete(id);
         return ResponseResult.okResult();
    }

    @Override
    public ResponseResult queryAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟分页设置的后的第一个select查询会被分页查询
        List<Goods> all = goodsMapper.findAll();
        //3.PageInfo参数navigatepage（导航页，显示的页码）：默认显示5个连续页,页码导航连续显示的页数5
        List<GoodsVo> goodsVos = BeanCopyUtils.copyBeanList(all, GoodsVo.class);
        for (int i = 0; i < goodsVos.size(); i++) {
            goodsVos.get(i).setGoodsId(all.get(i).getId());
            goodsVos.get(i).setOutCount(0);
        }
        PageInfo<GoodsVo> goodsPageInfo = new PageInfo<>(goodsVos, 5);
        return ResponseResult.okResult(goodsPageInfo);
    }

    @Override
    public ResponseResult addGoods(Goods goods) {
        Date date=new Date();
        goods.setCreateTime(date);
        int isDelete=1;
        goods.setIsDelete(isDelete);
         goodsMapper.insertGoods(goods);
         return ResponseResult.okResult();
    }

    @Override
    public ResponseResult updateGoods(Goods goods) {
        Date date=new Date();
        goods.setUpdateTime(date);
         goodsMapper.updateGoods(goods);
         return ResponseResult.okResult();
    }

    @Override
    public ResponseResult queryBy(Integer pageNum, Integer pageSize, String goodsName, String color) {
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟分页设置的后的第一个select查询会被分页查询
        List<Goods> all = goodsMapper.selectBy(goodsName,color);
        //3.PageInfo参数navigatepage（导航页，显示的页码）：默认显示5个连续页,页码导航连续显示的页数5
        PageInfo<Goods> goodsPageInfo = new PageInfo<>(all, 5);
        return ResponseResult.okResult(goodsPageInfo);

    }


}
