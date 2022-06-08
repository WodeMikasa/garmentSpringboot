package cn.ccsu.service.impl;

import cn.ccsu.entity.Goods;
import cn.ccsu.entity.OutStore;
import cn.ccsu.entity.OutStoreDetail;
import cn.ccsu.entity.OutStoreLink;
import cn.ccsu.enums.AppHttpCodeEnum;
import cn.ccsu.ex.SystemException;
import cn.ccsu.mapper.GoodsMapper;
import cn.ccsu.mapper.OutStoreDetailMapper;
import cn.ccsu.mapper.OutStoreLinkMapper;
import cn.ccsu.mapper.OutStoreMapper;
import cn.ccsu.service.OutStoreService;
import cn.ccsu.utils.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class OutStoreServiceImpl implements OutStoreService {
    @Autowired
    private OutStoreLinkMapper outStoreLinkMapper;
    @Autowired
    private OutStoreMapper outStoreMapper;
    @Autowired
    private OutStoreDetailMapper outStoreDetailMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult outStore(OutStore outStore) {
        outStore.setCreateTime(new Date());
        outStore.setNumber(UUID.randomUUID().toString().replaceAll("_",""));
        outStore.setIsDelete(1);
        outStore.setUpdateTime(new Date());
        Integer integer = outStoreMapper.insert(outStore);
        Integer id = outStore.getId();
        if (integer!=1){
            throw new SystemException(AppHttpCodeEnum.OUTSTORE_ERROR);
        }
        List<OutStoreDetail> detail = outStore.getDetail();
        for (OutStoreDetail outStoreDetail:detail){

            Integer goodsId = outStoreDetail.getGoodsId();
            Goods goods = goodsMapper.selectById(goodsId);
            if (Objects.isNull(goods)){
                throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
            }
            outStoreDetail.setName(goods.getName());
            outStoreDetail.setColor(goods.getColor());
            outStoreDetail.setSize(goods.getSize());
            outStoreDetail.setColor(goods.getColor());
            outStoreDetail.setCreateTime(new Date());
            outStoreDetail.setUpdateTime(new Date());
            Integer insert = outStoreDetailMapper.insert(outStoreDetail);
            if (insert!=1){
                throw new SystemException(AppHttpCodeEnum.OUTSTORE_ERROR2);
            }
            Integer count = outStoreDetail.getOutCount();
            if (count>goods.getCount()){
                throw new SystemException(AppHttpCodeEnum.OUTSTORE_ERROR3);

            }
            count=goods.getCount()-count;
            Integer integer1 = goodsMapper.updateCount(goodsId, count);
            if (integer1!=1){
                throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
            }
            OutStoreLink outStoreLink=new OutStoreLink();
            outStoreLink.setOutStoreId(id);
            outStoreLink.setOutStoreDetailId(outStoreDetail.getId());
            Integer insert1 = outStoreLinkMapper.insert(outStoreLink);
            if (insert1!=1){
                throw new SystemException(AppHttpCodeEnum.OUTSTORE_ERROR4);
            }


        }

        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult queryAll(Integer pageNum, Integer pageSize, String number, String storage) {
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟分页设置的后的第一个select查询会被分页查询
        List<OutStore> all = outStoreMapper.query(number,storage);
        //3.PageInfo参数navigatepage（导航页，显示的页码）：默认显示5个连续页,页码导航连续显示的页数5
        PageInfo<OutStore> pageInfo = new PageInfo<>(all, 5);
        return ResponseResult.okResult(pageInfo);
    }

    @Override
    public ResponseResult delete(Integer id) {
        Integer delete = outStoreMapper.delete(id);
        if (delete!=1){
            throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult update(OutStore outStore) {
        outStore.setUpdateTime(new Date());
        Integer update = outStoreMapper.update(outStore);
        if (update!=1){
            throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getById(Integer id) {
        if (Objects.isNull(id)){
            throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        OutStore outStore = outStoreMapper.selectById(id);
        if (Objects.isNull(outStore)){
            throw new SystemException(AppHttpCodeEnum.OUTSTORE_NULL);
        }
        return ResponseResult.okResult(outStore);
    }


}
